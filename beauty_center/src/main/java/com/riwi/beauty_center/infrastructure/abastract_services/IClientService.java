package com.riwi.beauty_center.infrastructure.abastract_services;

import com.riwi.beauty_center.api.dto.request.ClientRequest;
import com.riwi.beauty_center.api.dto.response.ClientResponse;

public interface IClientService extends CrudService<ClientRequest, ClientResponse, Long> {
}
