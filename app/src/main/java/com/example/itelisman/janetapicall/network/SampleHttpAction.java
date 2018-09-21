package com.example.itelisman.janetapicall.network;

import com.example.itelisman.janetapicall.model.SongsResponse;

import io.techery.janet.http.annotations.HttpAction;
import io.techery.janet.http.annotations.Query;
import io.techery.janet.http.annotations.Response;

import static io.techery.janet.http.annotations.HttpAction.Method.GET;

@HttpAction(value = "/search/", method = GET)
public class SampleHttpAction {
    @Query("term")
    String term;

    @Response
    public SongsResponse songsResponse;

    public SampleHttpAction(String term){
        this.term = term;
    }
}