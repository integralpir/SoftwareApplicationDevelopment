package ru.romanorlov.AmiibosAggregator.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.romanorlov.AmiibosAggregator.client.AmiiboClientImpl;
import ru.romanorlov.AmiibosAggregator.model.entity.GameSeries;
import ru.romanorlov.AmiibosAggregator.model.request.AmiiboApiResponse;
import ru.romanorlov.AmiibosAggregator.model.response.GameSeriesRs;
import ru.romanorlov.AmiibosAggregator.repository.AmiiboRepository;
import ru.romanorlov.AmiibosAggregator.repository.AmiiboSeriesRepository;
import ru.romanorlov.AmiibosAggregator.repository.GameSeriesRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ApplicationServiceImplTest {

    @Mock
    private AmiiboClientImpl client;

    @Mock
    private AmiiboRepository amiiboRepository;

    @Mock
    private GameSeriesRepository gameSeriesRepository;

    @Mock
    private AmiiboSeriesRepository amiiboSeriesRepository;

    @InjectMocks
    private ApplicationServiceImpl service;

    @Test
    void testPreloadingData() {
        AmiiboApiResponse amiiboApiResponse = mock(AmiiboApiResponse.class);
        when(client.getAmiibosFromInternalApi()).thenReturn(amiiboApiResponse);

        service.preloadingData();

        verify(amiiboRepository).deleteAll();
        verify(amiiboSeriesRepository).deleteAll();
        verify(gameSeriesRepository).deleteAll();
    }

    @Test
    void testGetAllGameSeries() {
        GameSeries gameSeries = mock(GameSeries.class);
        when(gameSeries.getTitle()).thenReturn("mock_title");
        List<GameSeries> repoGameSeriesList = List.of(gameSeries);
        when(gameSeriesRepository.findAll()).thenReturn(repoGameSeriesList);

        GameSeriesRs expectedGameSeries = mock(GameSeriesRs.class);
        when(expectedGameSeries.title()).thenReturn("mock_title");
        List<GameSeriesRs> expected = List.of(expectedGameSeries);

        List<GameSeriesRs> actual = service.getAllGameSeries();

        assertEquals(expected.size(), actual.size());
        assertEquals(expected.getFirst().title(), actual.getFirst().title());
    }
}
