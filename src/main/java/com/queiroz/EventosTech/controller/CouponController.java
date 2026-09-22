package com.queiroz.EventosTech.controller;

import com.queiroz.EventosTech.domain.coupon.Coupon;
import com.queiroz.EventosTech.domain.coupon.CouponRequestDTO;
import com.queiroz.EventosTech.services.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/coupons")
public class CouponController {

    @Autowired
    private CouponService couponService;

    @PostMapping("/event/{eventId}")
            public ResponseEntity<Coupon> addCouponToEvent(@PathVariable UUID eventId, @RequestBody CouponRequestDTO data) {
                Coupon coupons = couponService.addCouponToEvent(eventId, data);
                return ResponseEntity.ok(coupons);
            }


}
