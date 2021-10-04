package br.com.alelo.consumer.consumerpat.services;

import br.com.alelo.consumer.consumerpat.entity.Address;
import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.entity.Contact;
import br.com.alelo.consumer.consumerpat.entity.enums.CardType;
import br.com.alelo.consumer.consumerpat.respository.AddressRepository;
import br.com.alelo.consumer.consumerpat.respository.CardRepository;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.services.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@SpringBootTest
class ConsumerServiceTest {

    private static final Integer INDEX                  = 0;
    private static final Integer ID                     = 1;
    private static final String MOBILE_PHONE_NUMBER     = "43984634308";
    private static final String RESIDENCE_PHONE_NUMBER  = "4333393876";
    private static final String PHONE_NUMBER            = "43984990811";
    private static final String EMAIL                   = "valdircezar312@gmail.com";
    private static final String CARD_NUMBER             = "1111-2222-3333-4444";
    private static final String STREET                  = "Street";
    private static final String NUMBER                  = "Number";
    private static final String CITY                    = "City";
    private static final String COUNTRY                 = "Country";
    private static final String POSTAL_CODE             = "PostalCode";
    private static final Consumer CONSUMER              = new Consumer();
    private static final String BIRTH_DATE              = "12/02/1993";
    private static final String NAME                    = "Valdir";
    private static final String DOCUMENT_NUMBER         = "09129161925";
    private static final Double CARD_BALANCE            = 1000.0;
    private static final CardType CARD_TYPE             = CardType.FOOD;
    private static final String OBJECT_NOT_FOUND_EXCEPTION = "Objeto Não encontrado. Tipo: " + Consumer.class.getSimpleName();

    @InjectMocks
    private ConsumerService service;
    @Mock
    private CardRepository cardRepository;
    @Mock
    private AddressRepository addressRepository;
    @Mock
    private ConsumerRepository repository;
    @Mock
    private CardService cardService;

    private Consumer consumer = new Consumer();
    private Optional<Consumer> optionalConsumer;
    private Contact contact;
    private Address address;
    private Card card;

    @BeforeEach
    void setUp() throws ParseException {
        MockitoAnnotations.openMocks(this);
        startConsumers();
    }

    @Test
    void whenFindByIdThenReturnAnConsumerInstance() {
        when(repository.findById(anyInt())).thenReturn(optionalConsumer);

        Consumer response = service.findById(ID);

        assertNotNull(response);
        assertEquals(Consumer.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NAME, response.getName());
        assertEquals(DOCUMENT_NUMBER, response.getDocumentNumber());
        assertEquals(BIRTH_DATE, response.getBirthDate().toString());
    }


    @Test
    void whenFindByIdThenReturnAnObjectNotFoundException() {
        when(repository.findById(anyInt()))
                .thenThrow(new ObjectNotFoundException(OBJECT_NOT_FOUND_EXCEPTION));

        try {
            service.findById(ID);
        } catch (Exception ex) {
            assertEquals(ObjectNotFoundException.class, ex.getClass());
            assertEquals(OBJECT_NOT_FOUND_EXCEPTION, ex.getMessage());
        }
    }

    @Test
    void create() {
    }

    @Test
    void update() {
    }

    private void startConsumers() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        contact = new Contact(ID, MOBILE_PHONE_NUMBER, RESIDENCE_PHONE_NUMBER, PHONE_NUMBER, EMAIL, CONSUMER);
        card = new Card(ID, CARD_NUMBER, CARD_BALANCE, CARD_TYPE, CONSUMER);
        address = new Address(ID, STREET, NUMBER, CITY, COUNTRY, POSTAL_CODE, CONSUMER);
        consumer = new Consumer( ID, NAME, DOCUMENT_NUMBER, sdf.parse(BIRTH_DATE), contact, address, List.of(card));
        optionalConsumer = Optional.of(consumer);
    }
}