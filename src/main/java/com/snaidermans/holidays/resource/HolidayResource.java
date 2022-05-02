package com.snaidermans.holidays.resource;

import com.snaidermans.holidays.service.HolidayService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/holidayapi")
@ApiOperation("Holiday API")
public class HolidayResource {
    @Autowired
    private HolidayService holidayService;

    @RequestMapping(method = RequestMethod.GET, value = "/{country}/{date}")
    @ApiOperation(value = "Figure out if a request date is a holiday in a requested country if full date is requested." +
            "Calculate holidays count if only a year is requested")
    public String isHoliday(
            @ApiParam(name = "country", value = "Country code", example = "lv") @PathVariable String country,
            @ApiParam(name = "date", value = "Date", example = "ddMMyyyy/yyyy") @PathVariable String date) {
        return holidayService.isHoliday(country, date);
    }

    @ExceptionHandler({IllegalArgumentException.class, IllegalStateException.class})
    public String getExceptionMessage(Exception e) {
        return e.getMessage();
    }
}
