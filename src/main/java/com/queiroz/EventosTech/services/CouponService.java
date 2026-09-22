package com.queiroz.EventosTech.services;


import com.queiroz.EventosTech.domain.coupon.Coupon;
import com.queiroz.EventosTech.domain.coupon.CouponRequestDTO;
import com.queiroz.EventosTech.domain.event.Event;
import com.queiroz.EventosTech.repositories.CouponRepository;
import com.queiroz.EventosTech.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class CouponService {
    @Autowired
    private CouponRepository couponRepository;

    @Autowired
    private EventRepository eventRepository;

    public Coupon addCouponToEvent(UUID eventId, CouponRequestDTO couponData) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new IllegalArgumentException("Event not found"));

        Coupon coupon = new Coupon();
        coupon.setCode(couponData.code());
        coupon.setDiscount(couponData.discount());
        coupon.setValid(new Date(couponData.valid()));
        coupon.setEvent(event);

        return couponRepository.save(coupon);
    }
}
