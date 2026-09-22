package com.queiroz.EventosTech.services;

import com.queiroz.EventosTech.domain.address.Address;
import com.queiroz.EventosTech.domain.event.Event;
import com.queiroz.EventosTech.domain.event.EventRequestDTO;
import com.queiroz.EventosTech.repositories.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    public Address createAddres(EventRequestDTO data, Event event){

        Address newAddress = new Address();
        newAddress.setCity(data.city());
        newAddress.setUf(data.uf());
        newAddress.setEvent(event);

        addressRepository.save(newAddress);

        return newAddress;
    }

}
