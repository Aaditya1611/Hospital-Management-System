package com.hms.covidData.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.hms.covidData.entities.CountryData;
import com.hms.covidData.repositories.CountryDataRepo;

@ExtendWith(MockitoExtension.class)
@DisplayName("CountryDataService Unit Tests")
class CountryDataServiceTest {

    @Mock
    private CountryDataRepo countryDataRepo;

    private CountryData countryData;

    @InjectMocks
    private CountryDataService countryDataService;

    @BeforeEach
    void setUp() {
            this.countryData = CountryData.builder()
                .id(4)
                .countryName("china")
                .confirmedCases(43242L)
                .deaths(2423L)
                .recovered(123L)
                .activeCases(200L)
                .newCases(300L)
                .newDeaths(80)
                .newRecovered(100)
                .deathsPerHundredCases(BigDecimal.valueOf(12.12))
                .recoveredPerHundredCases(BigDecimal.valueOf(21.12))
                .deathsPerHundredRecovered(BigDecimal.valueOf(54.32))
                .confirmedLastWeek(34L)
                .weeklyChange(54L)
                .weeklyPercentIncrease(BigDecimal.valueOf(32.321))
                .whoRegion("Asia")
                .build();
    }

    @Nested
    @DisplayName("Country Data Crud Tests")
    class CountryServiceTests {

        @Test
        @DisplayName("Should increase the number of cases in the new_cases table")
        void updateNewCases() {
            // Given
            final Long count = 92L;
            final Integer id = 5;
            when(countryDataRepo.findById(id)).thenReturn(Optional.of(countryData));

            when(countryDataRepo.save(org.mockito.ArgumentMatchers.any(CountryData.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));
            // When

            CountryData result = countryDataService.updateNewCases(id, count);

            // Then
            assertEquals(countryData.getNewCases() + count, result);
            assertNotNull(result);

            verify(countryDataRepo).findById(id);
            verify(countryDataRepo).save(result);
        }
    }
}
