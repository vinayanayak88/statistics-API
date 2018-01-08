package com.n26.aggregator.service.handler;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.n26.aggregator.model.Statistics;
import com.n26.aggregator.model.StatisticsSummary;
import com.n26.aggregator.model.Transaction;
import com.n26.aggregator.service.StatisticsService;

/**
 * @author vinayanayak
 * @date 04-Jan-2018 StatisticsServiceHandler.java
 */
@Service
public class StatisticsServiceHandler implements StatisticsService {

	private static final Logger logger = LoggerFactory.getLogger(StatisticsServiceHandler.class);

	private static final int SECONDS_STAT = 60;
	private static final Map<Integer, Statistics> statisticsForLastMin = new ConcurrentHashMap<>(SECONDS_STAT);

	/**
	 * Calculates statistics for last 60 seconds when new transaction data is received.
	 * Calculated summary(sum, min,, max, count) is stored inside map(Key = second and value = Statistics) for every second new summary is created
     * or existing one is updated with latest statistics for that second
     * If previously inserted entry is outdated then it will be overwritten by new one(s) (if no new transaction entry is received
     * for that second then it'll be simply ignored during statistics retrieval).
	 */
	@Override
	public void computeStatistics(Transaction transaction) {
		logger.info("Computing statistics based on new received transaction => {}", transaction);

		if ((System.currentTimeMillis() - transaction.getTimestamp()) / 1000 < SECONDS_STAT) {
			int second = LocalDateTime
					.ofInstant(Instant.ofEpochMilli(transaction.getTimestamp()), ZoneId.systemDefault()).getSecond();
			statisticsForLastMin.compute(second, (k, v) -> {
				if (v == null || (System.currentTimeMillis() - v.getTimestamp()) / 1000 >= SECONDS_STAT) {
					v = new Statistics();
					v.setTimestamp(transaction.getTimestamp());
					v.setSum(transaction.getAmount());
					v.setMax(transaction.getAmount());
					v.setMin(transaction.getAmount());
					v.setCount(1l);
					return v;
				}
				v.setCount(v.getCount() + 1);
				v.setSum(v.getSum() + transaction.getAmount());
				if (Double.compare(transaction.getAmount(), v.getMax()) > 0)
					v.setMax(transaction.getAmount());
				if (Double.compare(transaction.getAmount(), v.getMin()) < 0)
					v.setMin(transaction.getAmount());
				return v;
			});
		}
	}

	/**
	 * Calculates and returns combined statistics summary based on statistics map (statisticsForLastMin).
	 */
	@Override
	public StatisticsSummary getStatistics() {
		StatisticsSummary summary = statisticsForLastMin.values().stream()
				.filter(s -> (System.currentTimeMillis() - s.getTimestamp()) / 1000 < SECONDS_STAT)
				.map(StatisticsSummary::new).reduce(new StatisticsSummary(), (s1, s2) -> {
					s1.setSum(s1.getSum() + s2.getSum());
					s1.setCount(s1.getCount() + s2.getCount());
					s1.setMax(Double.compare(s1.getMax(), s2.getMax()) > 0 ? s1.getMax() : s2.getMax());
					s1.setMin(Double.compare(s1.getMin(), s2.getMin()) < 0 ? s1.getMin() : s2.getMin());
					return s1;
				});

		summary.setMin(Double.compare(summary.getMin(), Double.MAX_VALUE) == 0 ? 0.0 : summary.getMin());
		summary.setMax(Double.compare(summary.getMax(), Double.MIN_VALUE) == 0 ? 0.0 : summary.getMax());
		summary.setAvg(summary.getCount() > 0l ? summary.getSum() / summary.getCount() : 0.0);

		logger.info("Statistics summary for last minute => {}", summary.toString());
		return summary;
	}

	public static Map<Integer, Statistics> getStatisticsforlastmin() {
		return statisticsForLastMin;
	}
	
}
