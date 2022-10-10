//package com.example.movieapi.service.dto.serializer;
//
//import com.fasterxml.jackson.core.JsonParser;
//import com.fasterxml.jackson.databind.DeserializationContext;
//import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
//
//import java.io.IOException;
//import java.math.BigDecimal;
//import java.text.DecimalFormat;
//import java.text.DecimalFormatSymbols;
//import java.text.ParseException;
//
///**
// * @author Mahdi Sharifi
// * @since 10/5/22
// */
//public class BoxOfficeSerializer extends StdDeserializer<Long> {
//
//    public BoxOfficeSerializer() {
//        this(null);
//    }
//
//    public BoxOfficeSerializer(Class<?> vc) {
//        super(vc);
//    }
//
//    @Override
//    public Long deserialize(JsonParser jsonparser, DeserializationContext context) throws IOException {
//        long result;
//        try {
//        result= Long.parseLong(jsonparser.getText().replace("$", "").replace(",",""));
//        } catch (Exception e) {
//            throw new RuntimeException("Exception "+e.getMessage()); //TODO
//        }
//        return result;
//    }
//}