package com.sylvester.ezypay.subscription;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service("subscriptionService")
public class SubscriptionServiceBean implements SubscriptionService {

    @Override
    public SubscriptionBean submit(SubscriptionBean requestBean) throws Exception {
        SubscriptionBean responseBean = new SubscriptionBean();

        List<String> invoiceDates = null;
        if (validateDate(requestBean.getStartDate(), requestBean.getEndDate())) {
            if (SubscriptionEnum.WEEKLY.getValue().equals(requestBean.getSubscriptionType())) {

                invoiceDates = getWeeklyInvoiceDates(requestBean.getStartDate(),
                        requestBean.getEndDate(), requestBean.getSubscriptionTime());

            } else if (SubscriptionEnum.MONTHLY.getValue()
                    .equals(requestBean.getSubscriptionType())) {
                invoiceDates = getMonthlyInvoiceDates(requestBean.getStartDate(),
                        requestBean.getEndDate(), requestBean.getSubscriptionTime());
            } else {
                // May throw custom user defined exception here
                throw new Exception();
            }
        } else {
            // May throw custom user defined exception here
            throw new Exception();
        }

        responseBean.setSubscriptionType(requestBean.getSubscriptionType());
        responseBean.setAmount(requestBean.getAmount());
        responseBean.setAmountCurrency(requestBean.getAmountCurrency());
        responseBean.setInvoiceDates(invoiceDates);

        return responseBean;
    }

    private boolean validateDate(String startDate, String endDate) {

        boolean validDate = false;

        DateTimeFormatter formatter = DateTimeFormatter
                .ofPattern(DateFormatEnum.Day_Month_Year.getDateFormat());

        LocalDate start = LocalDate.parse(startDate, formatter);
        LocalDate end = LocalDate.parse(endDate, formatter);

        long monthsBetween = ChronoUnit.MONTHS.between(start.withDayOfMonth(1),
                end.withDayOfMonth(1));

        if (monthsBetween <= 3) {
            validDate = true;
        }

        System.out.println("validDate :: " + validDate);

        return validDate;
    }

    private List<String> getWeeklyInvoiceDates(String startDate, String endDate,
            String subscriptionTime) throws Exception {

        List<String> response = new ArrayList<>();
        DateTimeFormatter localDateformatter = DateTimeFormatter
                .ofPattern(DateFormatEnum.Day_Month_Year.getDateFormat());
        DayOfWeek day = null;

        for (DayOfWeek dayOfWeek : DayOfWeek.values()) {
            if (dayOfWeek.name().equals(subscriptionTime.toUpperCase())) {
                day = dayOfWeek;
            }
        }

        System.out.println("day ::: " + day);

        if (day != null) {

            LocalDate start = LocalDate.parse(startDate, localDateformatter);
            LocalDate end = LocalDate.parse(endDate, localDateformatter);

            System.out.println("start.isBefore(end) ... " + start.isBefore(end));
            response.add(start.format(localDateformatter));
            while (start.isBefore(end)) {
                start = start.with(TemporalAdjusters.next(day));
                System.out.println("start ... " + start);
                response.add(start.format(localDateformatter));
            }

        } else {
            throw new Exception();
        }

        return response;
    }

    private List<String> getMonthlyInvoiceDates(String startDate, String endDate,
            String subscriptionTime) throws Exception {

        List<String> response = new ArrayList<>();
        DateTimeFormatter localDateformatter = DateTimeFormatter
                .ofPattern(DateFormatEnum.Day_Month_Year.getDateFormat());
        LocalDate start = LocalDate.parse(startDate, localDateformatter);
        LocalDate end = LocalDate.parse(endDate, localDateformatter);

        while (start.isBefore(end)) {
            LocalDate next = start.plusMonths(1);
            start = next.withDayOfMonth(
                    Math.min(Integer.parseInt(subscriptionTime), next.lengthOfMonth()));
            System.out.println("start ... " + start);
            response.add(start.format(localDateformatter));
        }

        return response;
    }
}
