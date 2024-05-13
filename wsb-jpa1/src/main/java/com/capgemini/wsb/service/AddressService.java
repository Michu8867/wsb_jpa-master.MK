package com.capgemini.wsb.service;

import com.capgemini.wsb.dto.AddressTO;

public interface AddressService
{
    public AddressTO findById(final Long id);
}
