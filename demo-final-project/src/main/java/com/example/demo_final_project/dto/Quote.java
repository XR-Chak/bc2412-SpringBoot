package com.example.demo_final_project.dto;

import java.util.List;
import com.example.demo_final_project.service.CrumbManager;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Quote {
    private Result[] result;
    private String error;  // Using String for simplicity, could be null

@Getter
@Setter
@Builder
public static class Result {// Main Result class containing all stock data
  private String language;
  private String region;
  private String quoteType;
  private String typeDisp;
  private String quoteSourceName;
  private boolean triggerable;
  private String customPriceAlertConfidence;
  private String currency;
  private String[] corporateActions;
  private long regularMarketTime;
  private String shortName;
  private String longName;
  private String marketState;
  private double epsTrailingTwelveMonths;
  private double epsForward;
  private double epsCurrentYear;
  private double priceEpsCurrentYear;
  private long sharesOutstanding;
  private double bookValue;
  private double fiftyDayAverage;
  private double fiftyDayAverageChange;
  private double fiftyDayAverageChangePercent;
  private double twoHundredDayAverage;
  private double twoHundredDayAverageChange;
  private double twoHundredDayAverageChangePercent;
  private long marketCap;
  private double forwardPE;
  private double priceToBook;
  private int sourceInterval;
  private int exchangeDataDelayedBy;
  private String averageAnalystRating;
  private boolean tradeable;
  private boolean cryptoTradeable;
  private String exchange;
  private String messageBoardId;
  private String exchangeTimezoneName;
  private String exchangeTimezoneShortName;
  private long gmtOffSetMilliseconds;
  private String market;
  private boolean esgPopulated;
  private double regularMarketChangePercent;
  private double regularMarketPrice;
  private boolean hasPrePostMarketData;
  private long firstTradeDateMilliseconds;
  private int priceHint;
  private double regularMarketChange;
  private double regularMarketDayHigh;
  private String regularMarketDayRange;
  private double regularMarketDayLow;
  private long regularMarketVolume;
  private double regularMarketPreviousClose;
  private double bid;
  private double ask;
  private int bidSize;
  private int askSize;
  private String fullExchangeName;
  private String financialCurrency;
  private double regularMarketOpen;
  private long averageDailyVolume3Month;
  private long averageDailyVolume10Day;
  private double fiftyTwoWeekLowChange;
  private double fiftyTwoWeekLowChangePercent;
  private String fiftyTwoWeekRange;
  private double fiftyTwoWeekHighChange;
  private double fiftyTwoWeekHighChangePercent;
  private double fiftyTwoWeekLow;
  private double fiftyTwoWeekHigh;
  private double fiftyTwoWeekChangePercent;
  private long earningsTimestamp;
  private long earningsTimestampStart;
  private long earningsTimestampEnd;
  private long earningsCallTimestampStart;
  private long earningsCallTimestampEnd;
  private boolean isEarningsDateEstimate;
  private double trailingAnnualDividendRate;
  private double trailingPE;
  private double dividendRate;
  private double trailingAnnualDividendYield;
  private double dividendYield;
  private String symbol;
}

}


