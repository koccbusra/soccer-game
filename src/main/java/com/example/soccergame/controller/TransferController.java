package com.example.soccergame.controller;

import com.example.soccergame.request.TransferOfferRequest;
import com.example.soccergame.request.TransferListRequest;
import com.example.soccergame.service.TransferService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/v1/transfer")
public class TransferController {

    private final TransferService transferService;

    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }

    @GetMapping
    public ResponseEntity read(@RequestParam Map<String, String> customQuery){
        TransferListRequest transferRequest = new TransferListRequest(customQuery);
        return new ResponseEntity(transferService.getTransferList(transferRequest), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity update(@RequestBody TransferOfferRequest request){

        return new ResponseEntity(transferService.offerToPLayer(request), HttpStatus.OK);
    }
}
