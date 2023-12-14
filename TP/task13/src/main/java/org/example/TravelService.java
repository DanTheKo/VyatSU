package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;

@Component
public class TravelService {
    @Autowired
    private Request request;

    @Autowired
    private TravelersCard travelersCard;

//    @Autowired
//    private TripService tripService;

    @Autowired
    private MailService mailService;


    public void order(String date, String arrival) {
        if (request.addTrip(date, arrival)) {
            request.setDateOfRegistration(LocalDate.of(2023, 12, 14));
            travelersCard.addToCard(request);
        } else {
            System.out.println("В заявке нет туров");
            return;
        }
        System.out.println("Количество оформленных туров: " + travelersCard.getTrips().size() + "\n");

        mailService.sendMailOfRequest(LocalDate.now());
        request.setTrips(new ArrayList<>());
    }
    public void printTrips() {
        request.printAllTrips();
    }

}