package ru.romanorlov.amiibo_aggregator.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.romanorlov.amiibo_aggregator.model.request.AmiiboApiResponse;

@FeignClient(value = "amiiboapi", url = "https://amiiboapi.com")
public interface AmiiboClient {

    @RequestMapping(method = RequestMethod.GET, value = "/api/amiibo/")
    AmiiboApiResponse getAmiibos();

}
