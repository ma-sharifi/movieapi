package com.example.movieapi.service.dto.serializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;

/**
 * @author Mahdi Sharifi
 * @since 10/4/22
 */
public class BoxOfficeSerializer extends StdDeserializer<BigDecimal> {


    public BoxOfficeSerializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public BigDecimal deserialize(
            JsonParser jsonparser, DeserializationContext context)
            throws IOException {

        DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols();
        decimalFormatSymbols.setGroupingSeparator(',');
        decimalFormatSymbols.setDecimalSeparator('.');
        String pattern = "#,##0.0#";
        DecimalFormat decimalFormat = new DecimalFormat(pattern, decimalFormatSymbols);
        decimalFormat.setParseBigDecimal(true);

        try {
            return (BigDecimal) decimalFormat.parse(jsonparser.getText().replace("$", ""));
        } catch (ParseException e) {
            throw new RuntimeException("Exception parsing box office.");
        }
    }
}