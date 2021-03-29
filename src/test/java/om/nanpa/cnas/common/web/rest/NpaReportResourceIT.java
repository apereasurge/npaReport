package om.nanpa.cnas.common.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import om.nanpa.cnas.common.IntegrationTest;
import om.nanpa.cnas.common.domain.NpaReport;
import om.nanpa.cnas.common.repository.NpaReportRepository;
import om.nanpa.cnas.common.service.criteria.NpaReportCriteria;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link NpaReportResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class NpaReportResourceIT {

    private static final Long DEFAULT_NPA_ID = 1L;
    private static final Long UPDATED_NPA_ID = 2L;
    private static final Long SMALLER_NPA_ID = 1L - 1L;

    private static final String DEFAULT_TYPE_OF_CODE = "AAAAAAAAAA";
    private static final String UPDATED_TYPE_OF_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_ASSIGNABLE = "AAAAAAAAAA";
    private static final String UPDATED_ASSIGNABLE = "BBBBBBBBBB";

    private static final String DEFAULT_EXPLANATION = "AAAAAAAAAA";
    private static final String UPDATED_EXPLANATION = "BBBBBBBBBB";

    private static final String DEFAULT_RESERVED = "AAAAAAAAAA";
    private static final String UPDATED_RESERVED = "BBBBBBBBBB";

    private static final String DEFAULT_ASSIGNED = "AAAAAAAAAA";
    private static final String UPDATED_ASSIGNED = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_ASSIGNMENT_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_ASSIGNMENT_DATE = LocalDate.now(ZoneId.systemDefault());
    private static final LocalDate SMALLER_ASSIGNMENT_DATE = LocalDate.ofEpochDay(-1L);

    private static final String DEFAULT_USE_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_USE_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_LOCATION = "AAAAAAAAAA";
    private static final String UPDATED_LOCATION = "BBBBBBBBBB";

    private static final String DEFAULT_COUNTRY = "AAAAAAAAAA";
    private static final String UPDATED_COUNTRY = "BBBBBBBBBB";

    private static final String DEFAULT_IN_SERVICE = "AAAAAAAAAA";
    private static final String UPDATED_IN_SERVICE = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_IN_SERVICE_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_IN_SERVICE_DATE = LocalDate.now(ZoneId.systemDefault());
    private static final LocalDate SMALLER_IN_SERVICE_DATE = LocalDate.ofEpochDay(-1L);

    private static final String DEFAULT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_PLANNING_LETTERS = "AAAAAAAAAA";
    private static final String UPDATED_PLANNING_LETTERS = "BBBBBBBBBB";

    private static final String DEFAULT_NOTES = "AAAAAAAAAA";
    private static final String UPDATED_NOTES = "BBBBBBBBBB";

    private static final String DEFAULT_OVERLAY = "AAAAAAAAAA";
    private static final String UPDATED_OVERLAY = "BBBBBBBBBB";

    private static final String DEFAULT_OVERLAY_COMPLEX = "AAAAAAAAAA";
    private static final String UPDATED_OVERLAY_COMPLEX = "BBBBBBBBBB";

    private static final Long DEFAULT_PARENT_NPA_ID = 1L;
    private static final Long UPDATED_PARENT_NPA_ID = 2L;
    private static final Long SMALLER_PARENT_NPA_ID = 1L - 1L;

    private static final String DEFAULT_SERVICE = "AAAAAAAAAA";
    private static final String UPDATED_SERVICE = "BBBBBBBBBB";

    private static final String DEFAULT_TIME_ZONE = "AAAAAAAAAA";
    private static final String UPDATED_TIME_ZONE = "BBBBBBBBBB";

    private static final String DEFAULT_AREA_SERVED = "AAAAAAAAAA";
    private static final String UPDATED_AREA_SERVED = "BBBBBBBBBB";

    private static final String DEFAULT_MAP = "AAAAAAAAAA";
    private static final String UPDATED_MAP = "BBBBBBBBBB";

    private static final String DEFAULT_IN_JEOPARDY = "AAAAAAAAAA";
    private static final String UPDATED_IN_JEOPARDY = "BBBBBBBBBB";

    private static final String DEFAULT_RELIEF_PLANNING_IN_PROGRESS = "AAAAAAAAAA";
    private static final String UPDATED_RELIEF_PLANNING_IN_PROGRESS = "BBBBBBBBBB";

    private static final String DEFAULT_HOME_NPA_LOCAL_CALLS = "AAAAAAAAAA";
    private static final String UPDATED_HOME_NPA_LOCAL_CALLS = "BBBBBBBBBB";

    private static final String DEFAULT_HOME_NPA_TOLL_CALLS = "AAAAAAAAAA";
    private static final String UPDATED_HOME_NPA_TOLL_CALLS = "BBBBBBBBBB";

    private static final String DEFAULT_FOREIGN_NPA_LOCAL_CALLS = "AAAAAAAAAA";
    private static final String UPDATED_FOREIGN_NPA_LOCAL_CALLS = "BBBBBBBBBB";

    private static final String DEFAULT_FOREIGN_NPA_TOLL_CALLS = "AAAAAAAAAA";
    private static final String UPDATED_FOREIGN_NPA_TOLL_CALLS = "BBBBBBBBBB";

    private static final String DEFAULT_PERM_HNPA_LOCAL_CALLS = "AAAAAAAAAA";
    private static final String UPDATED_PERM_HNPA_LOCAL_CALLS = "BBBBBBBBBB";

    private static final String DEFAULT_PERM_HNPA_TOLL_CALLS = "AAAAAAAAAA";
    private static final String UPDATED_PERM_HNPA_TOLL_CALLS = "BBBBBBBBBB";

    private static final String DEFAULT_PERM_HNPA_FOREIGN_LOCAL_CALLS = "AAAAAAAAAA";
    private static final String UPDATED_PERM_HNPA_FOREIGN_LOCAL_CALLS = "BBBBBBBBBB";

    private static final String DEFAULT_DIALING_PLAN_NOTES = "AAAAAAAAAA";
    private static final String UPDATED_DIALING_PLAN_NOTES = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/npa-reports";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private NpaReportRepository npaReportRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restNpaReportMockMvc;

    private NpaReport npaReport;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static NpaReport createEntity(EntityManager em) {
        NpaReport npaReport = new NpaReport()
            .npaId(DEFAULT_NPA_ID)
            .typeOfCode(DEFAULT_TYPE_OF_CODE)
            .assignable(DEFAULT_ASSIGNABLE)
            .explanation(DEFAULT_EXPLANATION)
            .reserved(DEFAULT_RESERVED)
            .assigned(DEFAULT_ASSIGNED)
            .assignmentDate(DEFAULT_ASSIGNMENT_DATE)
            .useType(DEFAULT_USE_TYPE)
            .location(DEFAULT_LOCATION)
            .country(DEFAULT_COUNTRY)
            .inService(DEFAULT_IN_SERVICE)
            .inServiceDate(DEFAULT_IN_SERVICE_DATE)
            .status(DEFAULT_STATUS)
            .planningLetters(DEFAULT_PLANNING_LETTERS)
            .notes(DEFAULT_NOTES)
            .overlay(DEFAULT_OVERLAY)
            .overlayComplex(DEFAULT_OVERLAY_COMPLEX)
            .parentNpaId(DEFAULT_PARENT_NPA_ID)
            .service(DEFAULT_SERVICE)
            .timeZone(DEFAULT_TIME_ZONE)
            .areaServed(DEFAULT_AREA_SERVED)
            .map(DEFAULT_MAP)
            .inJeopardy(DEFAULT_IN_JEOPARDY)
            .reliefPlanningInProgress(DEFAULT_RELIEF_PLANNING_IN_PROGRESS)
            .homeNpaLocalCalls(DEFAULT_HOME_NPA_LOCAL_CALLS)
            .homeNpaTollCalls(DEFAULT_HOME_NPA_TOLL_CALLS)
            .foreignNpaLocalCalls(DEFAULT_FOREIGN_NPA_LOCAL_CALLS)
            .foreignNpaTollCalls(DEFAULT_FOREIGN_NPA_TOLL_CALLS)
            .permHnpaLocalCalls(DEFAULT_PERM_HNPA_LOCAL_CALLS)
            .permHnpaTollCalls(DEFAULT_PERM_HNPA_TOLL_CALLS)
            .permHnpaForeignLocalCalls(DEFAULT_PERM_HNPA_FOREIGN_LOCAL_CALLS)
            .dialingPlanNotes(DEFAULT_DIALING_PLAN_NOTES);
        return npaReport;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static NpaReport createUpdatedEntity(EntityManager em) {
        NpaReport npaReport = new NpaReport()
            .npaId(UPDATED_NPA_ID)
            .typeOfCode(UPDATED_TYPE_OF_CODE)
            .assignable(UPDATED_ASSIGNABLE)
            .explanation(UPDATED_EXPLANATION)
            .reserved(UPDATED_RESERVED)
            .assigned(UPDATED_ASSIGNED)
            .assignmentDate(UPDATED_ASSIGNMENT_DATE)
            .useType(UPDATED_USE_TYPE)
            .location(UPDATED_LOCATION)
            .country(UPDATED_COUNTRY)
            .inService(UPDATED_IN_SERVICE)
            .inServiceDate(UPDATED_IN_SERVICE_DATE)
            .status(UPDATED_STATUS)
            .planningLetters(UPDATED_PLANNING_LETTERS)
            .notes(UPDATED_NOTES)
            .overlay(UPDATED_OVERLAY)
            .overlayComplex(UPDATED_OVERLAY_COMPLEX)
            .parentNpaId(UPDATED_PARENT_NPA_ID)
            .service(UPDATED_SERVICE)
            .timeZone(UPDATED_TIME_ZONE)
            .areaServed(UPDATED_AREA_SERVED)
            .map(UPDATED_MAP)
            .inJeopardy(UPDATED_IN_JEOPARDY)
            .reliefPlanningInProgress(UPDATED_RELIEF_PLANNING_IN_PROGRESS)
            .homeNpaLocalCalls(UPDATED_HOME_NPA_LOCAL_CALLS)
            .homeNpaTollCalls(UPDATED_HOME_NPA_TOLL_CALLS)
            .foreignNpaLocalCalls(UPDATED_FOREIGN_NPA_LOCAL_CALLS)
            .foreignNpaTollCalls(UPDATED_FOREIGN_NPA_TOLL_CALLS)
            .permHnpaLocalCalls(UPDATED_PERM_HNPA_LOCAL_CALLS)
            .permHnpaTollCalls(UPDATED_PERM_HNPA_TOLL_CALLS)
            .permHnpaForeignLocalCalls(UPDATED_PERM_HNPA_FOREIGN_LOCAL_CALLS)
            .dialingPlanNotes(UPDATED_DIALING_PLAN_NOTES);
        return npaReport;
    }

    @BeforeEach
    public void initTest() {
        npaReport = createEntity(em);
    }

    @Test
    @Transactional
    void createNpaReport() throws Exception {
        int databaseSizeBeforeCreate = npaReportRepository.findAll().size();
        // Create the NpaReport
        restNpaReportMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(npaReport)))
            .andExpect(status().isCreated());

        // Validate the NpaReport in the database
        List<NpaReport> npaReportList = npaReportRepository.findAll();
        assertThat(npaReportList).hasSize(databaseSizeBeforeCreate + 1);
        NpaReport testNpaReport = npaReportList.get(npaReportList.size() - 1);
        assertThat(testNpaReport.getNpaId()).isEqualTo(DEFAULT_NPA_ID);
        assertThat(testNpaReport.getTypeOfCode()).isEqualTo(DEFAULT_TYPE_OF_CODE);
        assertThat(testNpaReport.getAssignable()).isEqualTo(DEFAULT_ASSIGNABLE);
        assertThat(testNpaReport.getExplanation()).isEqualTo(DEFAULT_EXPLANATION);
        assertThat(testNpaReport.getReserved()).isEqualTo(DEFAULT_RESERVED);
        assertThat(testNpaReport.getAssigned()).isEqualTo(DEFAULT_ASSIGNED);
        assertThat(testNpaReport.getAssignmentDate()).isEqualTo(DEFAULT_ASSIGNMENT_DATE);
        assertThat(testNpaReport.getUseType()).isEqualTo(DEFAULT_USE_TYPE);
        assertThat(testNpaReport.getLocation()).isEqualTo(DEFAULT_LOCATION);
        assertThat(testNpaReport.getCountry()).isEqualTo(DEFAULT_COUNTRY);
        assertThat(testNpaReport.getInService()).isEqualTo(DEFAULT_IN_SERVICE);
        assertThat(testNpaReport.getInServiceDate()).isEqualTo(DEFAULT_IN_SERVICE_DATE);
        assertThat(testNpaReport.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testNpaReport.getPlanningLetters()).isEqualTo(DEFAULT_PLANNING_LETTERS);
        assertThat(testNpaReport.getNotes()).isEqualTo(DEFAULT_NOTES);
        assertThat(testNpaReport.getOverlay()).isEqualTo(DEFAULT_OVERLAY);
        assertThat(testNpaReport.getOverlayComplex()).isEqualTo(DEFAULT_OVERLAY_COMPLEX);
        assertThat(testNpaReport.getParentNpaId()).isEqualTo(DEFAULT_PARENT_NPA_ID);
        assertThat(testNpaReport.getService()).isEqualTo(DEFAULT_SERVICE);
        assertThat(testNpaReport.getTimeZone()).isEqualTo(DEFAULT_TIME_ZONE);
        assertThat(testNpaReport.getAreaServed()).isEqualTo(DEFAULT_AREA_SERVED);
        assertThat(testNpaReport.getMap()).isEqualTo(DEFAULT_MAP);
        assertThat(testNpaReport.getInJeopardy()).isEqualTo(DEFAULT_IN_JEOPARDY);
        assertThat(testNpaReport.getReliefPlanningInProgress()).isEqualTo(DEFAULT_RELIEF_PLANNING_IN_PROGRESS);
        assertThat(testNpaReport.getHomeNpaLocalCalls()).isEqualTo(DEFAULT_HOME_NPA_LOCAL_CALLS);
        assertThat(testNpaReport.getHomeNpaTollCalls()).isEqualTo(DEFAULT_HOME_NPA_TOLL_CALLS);
        assertThat(testNpaReport.getForeignNpaLocalCalls()).isEqualTo(DEFAULT_FOREIGN_NPA_LOCAL_CALLS);
        assertThat(testNpaReport.getForeignNpaTollCalls()).isEqualTo(DEFAULT_FOREIGN_NPA_TOLL_CALLS);
        assertThat(testNpaReport.getPermHnpaLocalCalls()).isEqualTo(DEFAULT_PERM_HNPA_LOCAL_CALLS);
        assertThat(testNpaReport.getPermHnpaTollCalls()).isEqualTo(DEFAULT_PERM_HNPA_TOLL_CALLS);
        assertThat(testNpaReport.getPermHnpaForeignLocalCalls()).isEqualTo(DEFAULT_PERM_HNPA_FOREIGN_LOCAL_CALLS);
        assertThat(testNpaReport.getDialingPlanNotes()).isEqualTo(DEFAULT_DIALING_PLAN_NOTES);
    }

    @Test
    @Transactional
    void createNpaReportWithExistingId() throws Exception {
        // Create the NpaReport with an existing ID
        npaReport.setId(1L);

        int databaseSizeBeforeCreate = npaReportRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restNpaReportMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(npaReport)))
            .andExpect(status().isBadRequest());

        // Validate the NpaReport in the database
        List<NpaReport> npaReportList = npaReportRepository.findAll();
        assertThat(npaReportList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllNpaReports() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList
        restNpaReportMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(npaReport.getId().intValue())))
            .andExpect(jsonPath("$.[*].npaId").value(hasItem(DEFAULT_NPA_ID.intValue())))
            .andExpect(jsonPath("$.[*].typeOfCode").value(hasItem(DEFAULT_TYPE_OF_CODE)))
            .andExpect(jsonPath("$.[*].assignable").value(hasItem(DEFAULT_ASSIGNABLE)))
            .andExpect(jsonPath("$.[*].explanation").value(hasItem(DEFAULT_EXPLANATION)))
            .andExpect(jsonPath("$.[*].reserved").value(hasItem(DEFAULT_RESERVED)))
            .andExpect(jsonPath("$.[*].assigned").value(hasItem(DEFAULT_ASSIGNED)))
            .andExpect(jsonPath("$.[*].assignmentDate").value(hasItem(DEFAULT_ASSIGNMENT_DATE.toString())))
            .andExpect(jsonPath("$.[*].useType").value(hasItem(DEFAULT_USE_TYPE)))
            .andExpect(jsonPath("$.[*].location").value(hasItem(DEFAULT_LOCATION)))
            .andExpect(jsonPath("$.[*].country").value(hasItem(DEFAULT_COUNTRY)))
            .andExpect(jsonPath("$.[*].inService").value(hasItem(DEFAULT_IN_SERVICE)))
            .andExpect(jsonPath("$.[*].inServiceDate").value(hasItem(DEFAULT_IN_SERVICE_DATE.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].planningLetters").value(hasItem(DEFAULT_PLANNING_LETTERS)))
            .andExpect(jsonPath("$.[*].notes").value(hasItem(DEFAULT_NOTES)))
            .andExpect(jsonPath("$.[*].overlay").value(hasItem(DEFAULT_OVERLAY)))
            .andExpect(jsonPath("$.[*].overlayComplex").value(hasItem(DEFAULT_OVERLAY_COMPLEX)))
            .andExpect(jsonPath("$.[*].parentNpaId").value(hasItem(DEFAULT_PARENT_NPA_ID.intValue())))
            .andExpect(jsonPath("$.[*].service").value(hasItem(DEFAULT_SERVICE)))
            .andExpect(jsonPath("$.[*].timeZone").value(hasItem(DEFAULT_TIME_ZONE)))
            .andExpect(jsonPath("$.[*].areaServed").value(hasItem(DEFAULT_AREA_SERVED)))
            .andExpect(jsonPath("$.[*].map").value(hasItem(DEFAULT_MAP)))
            .andExpect(jsonPath("$.[*].inJeopardy").value(hasItem(DEFAULT_IN_JEOPARDY)))
            .andExpect(jsonPath("$.[*].reliefPlanningInProgress").value(hasItem(DEFAULT_RELIEF_PLANNING_IN_PROGRESS)))
            .andExpect(jsonPath("$.[*].homeNpaLocalCalls").value(hasItem(DEFAULT_HOME_NPA_LOCAL_CALLS)))
            .andExpect(jsonPath("$.[*].homeNpaTollCalls").value(hasItem(DEFAULT_HOME_NPA_TOLL_CALLS)))
            .andExpect(jsonPath("$.[*].foreignNpaLocalCalls").value(hasItem(DEFAULT_FOREIGN_NPA_LOCAL_CALLS)))
            .andExpect(jsonPath("$.[*].foreignNpaTollCalls").value(hasItem(DEFAULT_FOREIGN_NPA_TOLL_CALLS)))
            .andExpect(jsonPath("$.[*].permHnpaLocalCalls").value(hasItem(DEFAULT_PERM_HNPA_LOCAL_CALLS)))
            .andExpect(jsonPath("$.[*].permHnpaTollCalls").value(hasItem(DEFAULT_PERM_HNPA_TOLL_CALLS)))
            .andExpect(jsonPath("$.[*].permHnpaForeignLocalCalls").value(hasItem(DEFAULT_PERM_HNPA_FOREIGN_LOCAL_CALLS)))
            .andExpect(jsonPath("$.[*].dialingPlanNotes").value(hasItem(DEFAULT_DIALING_PLAN_NOTES)));
    }

    @Test
    @Transactional
    void getNpaReport() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get the npaReport
        restNpaReportMockMvc
            .perform(get(ENTITY_API_URL_ID, npaReport.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(npaReport.getId().intValue()))
            .andExpect(jsonPath("$.npaId").value(DEFAULT_NPA_ID.intValue()))
            .andExpect(jsonPath("$.typeOfCode").value(DEFAULT_TYPE_OF_CODE))
            .andExpect(jsonPath("$.assignable").value(DEFAULT_ASSIGNABLE))
            .andExpect(jsonPath("$.explanation").value(DEFAULT_EXPLANATION))
            .andExpect(jsonPath("$.reserved").value(DEFAULT_RESERVED))
            .andExpect(jsonPath("$.assigned").value(DEFAULT_ASSIGNED))
            .andExpect(jsonPath("$.assignmentDate").value(DEFAULT_ASSIGNMENT_DATE.toString()))
            .andExpect(jsonPath("$.useType").value(DEFAULT_USE_TYPE))
            .andExpect(jsonPath("$.location").value(DEFAULT_LOCATION))
            .andExpect(jsonPath("$.country").value(DEFAULT_COUNTRY))
            .andExpect(jsonPath("$.inService").value(DEFAULT_IN_SERVICE))
            .andExpect(jsonPath("$.inServiceDate").value(DEFAULT_IN_SERVICE_DATE.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.planningLetters").value(DEFAULT_PLANNING_LETTERS))
            .andExpect(jsonPath("$.notes").value(DEFAULT_NOTES))
            .andExpect(jsonPath("$.overlay").value(DEFAULT_OVERLAY))
            .andExpect(jsonPath("$.overlayComplex").value(DEFAULT_OVERLAY_COMPLEX))
            .andExpect(jsonPath("$.parentNpaId").value(DEFAULT_PARENT_NPA_ID.intValue()))
            .andExpect(jsonPath("$.service").value(DEFAULT_SERVICE))
            .andExpect(jsonPath("$.timeZone").value(DEFAULT_TIME_ZONE))
            .andExpect(jsonPath("$.areaServed").value(DEFAULT_AREA_SERVED))
            .andExpect(jsonPath("$.map").value(DEFAULT_MAP))
            .andExpect(jsonPath("$.inJeopardy").value(DEFAULT_IN_JEOPARDY))
            .andExpect(jsonPath("$.reliefPlanningInProgress").value(DEFAULT_RELIEF_PLANNING_IN_PROGRESS))
            .andExpect(jsonPath("$.homeNpaLocalCalls").value(DEFAULT_HOME_NPA_LOCAL_CALLS))
            .andExpect(jsonPath("$.homeNpaTollCalls").value(DEFAULT_HOME_NPA_TOLL_CALLS))
            .andExpect(jsonPath("$.foreignNpaLocalCalls").value(DEFAULT_FOREIGN_NPA_LOCAL_CALLS))
            .andExpect(jsonPath("$.foreignNpaTollCalls").value(DEFAULT_FOREIGN_NPA_TOLL_CALLS))
            .andExpect(jsonPath("$.permHnpaLocalCalls").value(DEFAULT_PERM_HNPA_LOCAL_CALLS))
            .andExpect(jsonPath("$.permHnpaTollCalls").value(DEFAULT_PERM_HNPA_TOLL_CALLS))
            .andExpect(jsonPath("$.permHnpaForeignLocalCalls").value(DEFAULT_PERM_HNPA_FOREIGN_LOCAL_CALLS))
            .andExpect(jsonPath("$.dialingPlanNotes").value(DEFAULT_DIALING_PLAN_NOTES));
    }

    @Test
    @Transactional
    void getNpaReportsByIdFiltering() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        Long id = npaReport.getId();

        defaultNpaReportShouldBeFound("id.equals=" + id);
        defaultNpaReportShouldNotBeFound("id.notEquals=" + id);

        defaultNpaReportShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultNpaReportShouldNotBeFound("id.greaterThan=" + id);

        defaultNpaReportShouldBeFound("id.lessThanOrEqual=" + id);
        defaultNpaReportShouldNotBeFound("id.lessThan=" + id);
    }

    @Test
    @Transactional
    void getAllNpaReportsByNpaIdIsEqualToSomething() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where npaId equals to DEFAULT_NPA_ID
        defaultNpaReportShouldBeFound("npaId.equals=" + DEFAULT_NPA_ID);

        // Get all the npaReportList where npaId equals to UPDATED_NPA_ID
        defaultNpaReportShouldNotBeFound("npaId.equals=" + UPDATED_NPA_ID);
    }

    @Test
    @Transactional
    void getAllNpaReportsByNpaIdIsNotEqualToSomething() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where npaId not equals to DEFAULT_NPA_ID
        defaultNpaReportShouldNotBeFound("npaId.notEquals=" + DEFAULT_NPA_ID);

        // Get all the npaReportList where npaId not equals to UPDATED_NPA_ID
        defaultNpaReportShouldBeFound("npaId.notEquals=" + UPDATED_NPA_ID);
    }

    @Test
    @Transactional
    void getAllNpaReportsByNpaIdIsInShouldWork() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where npaId in DEFAULT_NPA_ID or UPDATED_NPA_ID
        defaultNpaReportShouldBeFound("npaId.in=" + DEFAULT_NPA_ID + "," + UPDATED_NPA_ID);

        // Get all the npaReportList where npaId equals to UPDATED_NPA_ID
        defaultNpaReportShouldNotBeFound("npaId.in=" + UPDATED_NPA_ID);
    }

    @Test
    @Transactional
    void getAllNpaReportsByNpaIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where npaId is not null
        defaultNpaReportShouldBeFound("npaId.specified=true");

        // Get all the npaReportList where npaId is null
        defaultNpaReportShouldNotBeFound("npaId.specified=false");
    }

    @Test
    @Transactional
    void getAllNpaReportsByNpaIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where npaId is greater than or equal to DEFAULT_NPA_ID
        defaultNpaReportShouldBeFound("npaId.greaterThanOrEqual=" + DEFAULT_NPA_ID);

        // Get all the npaReportList where npaId is greater than or equal to UPDATED_NPA_ID
        defaultNpaReportShouldNotBeFound("npaId.greaterThanOrEqual=" + UPDATED_NPA_ID);
    }

    @Test
    @Transactional
    void getAllNpaReportsByNpaIdIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where npaId is less than or equal to DEFAULT_NPA_ID
        defaultNpaReportShouldBeFound("npaId.lessThanOrEqual=" + DEFAULT_NPA_ID);

        // Get all the npaReportList where npaId is less than or equal to SMALLER_NPA_ID
        defaultNpaReportShouldNotBeFound("npaId.lessThanOrEqual=" + SMALLER_NPA_ID);
    }

    @Test
    @Transactional
    void getAllNpaReportsByNpaIdIsLessThanSomething() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where npaId is less than DEFAULT_NPA_ID
        defaultNpaReportShouldNotBeFound("npaId.lessThan=" + DEFAULT_NPA_ID);

        // Get all the npaReportList where npaId is less than UPDATED_NPA_ID
        defaultNpaReportShouldBeFound("npaId.lessThan=" + UPDATED_NPA_ID);
    }

    @Test
    @Transactional
    void getAllNpaReportsByNpaIdIsGreaterThanSomething() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where npaId is greater than DEFAULT_NPA_ID
        defaultNpaReportShouldNotBeFound("npaId.greaterThan=" + DEFAULT_NPA_ID);

        // Get all the npaReportList where npaId is greater than SMALLER_NPA_ID
        defaultNpaReportShouldBeFound("npaId.greaterThan=" + SMALLER_NPA_ID);
    }

    @Test
    @Transactional
    void getAllNpaReportsByTypeOfCodeIsEqualToSomething() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where typeOfCode equals to DEFAULT_TYPE_OF_CODE
        defaultNpaReportShouldBeFound("typeOfCode.equals=" + DEFAULT_TYPE_OF_CODE);

        // Get all the npaReportList where typeOfCode equals to UPDATED_TYPE_OF_CODE
        defaultNpaReportShouldNotBeFound("typeOfCode.equals=" + UPDATED_TYPE_OF_CODE);
    }

    @Test
    @Transactional
    void getAllNpaReportsByTypeOfCodeIsNotEqualToSomething() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where typeOfCode not equals to DEFAULT_TYPE_OF_CODE
        defaultNpaReportShouldNotBeFound("typeOfCode.notEquals=" + DEFAULT_TYPE_OF_CODE);

        // Get all the npaReportList where typeOfCode not equals to UPDATED_TYPE_OF_CODE
        defaultNpaReportShouldBeFound("typeOfCode.notEquals=" + UPDATED_TYPE_OF_CODE);
    }

    @Test
    @Transactional
    void getAllNpaReportsByTypeOfCodeIsInShouldWork() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where typeOfCode in DEFAULT_TYPE_OF_CODE or UPDATED_TYPE_OF_CODE
        defaultNpaReportShouldBeFound("typeOfCode.in=" + DEFAULT_TYPE_OF_CODE + "," + UPDATED_TYPE_OF_CODE);

        // Get all the npaReportList where typeOfCode equals to UPDATED_TYPE_OF_CODE
        defaultNpaReportShouldNotBeFound("typeOfCode.in=" + UPDATED_TYPE_OF_CODE);
    }

    @Test
    @Transactional
    void getAllNpaReportsByTypeOfCodeIsNullOrNotNull() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where typeOfCode is not null
        defaultNpaReportShouldBeFound("typeOfCode.specified=true");

        // Get all the npaReportList where typeOfCode is null
        defaultNpaReportShouldNotBeFound("typeOfCode.specified=false");
    }

    @Test
    @Transactional
    void getAllNpaReportsByTypeOfCodeContainsSomething() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where typeOfCode contains DEFAULT_TYPE_OF_CODE
        defaultNpaReportShouldBeFound("typeOfCode.contains=" + DEFAULT_TYPE_OF_CODE);

        // Get all the npaReportList where typeOfCode contains UPDATED_TYPE_OF_CODE
        defaultNpaReportShouldNotBeFound("typeOfCode.contains=" + UPDATED_TYPE_OF_CODE);
    }

    @Test
    @Transactional
    void getAllNpaReportsByTypeOfCodeNotContainsSomething() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where typeOfCode does not contain DEFAULT_TYPE_OF_CODE
        defaultNpaReportShouldNotBeFound("typeOfCode.doesNotContain=" + DEFAULT_TYPE_OF_CODE);

        // Get all the npaReportList where typeOfCode does not contain UPDATED_TYPE_OF_CODE
        defaultNpaReportShouldBeFound("typeOfCode.doesNotContain=" + UPDATED_TYPE_OF_CODE);
    }

    @Test
    @Transactional
    void getAllNpaReportsByAssignableIsEqualToSomething() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where assignable equals to DEFAULT_ASSIGNABLE
        defaultNpaReportShouldBeFound("assignable.equals=" + DEFAULT_ASSIGNABLE);

        // Get all the npaReportList where assignable equals to UPDATED_ASSIGNABLE
        defaultNpaReportShouldNotBeFound("assignable.equals=" + UPDATED_ASSIGNABLE);
    }

    @Test
    @Transactional
    void getAllNpaReportsByAssignableIsNotEqualToSomething() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where assignable not equals to DEFAULT_ASSIGNABLE
        defaultNpaReportShouldNotBeFound("assignable.notEquals=" + DEFAULT_ASSIGNABLE);

        // Get all the npaReportList where assignable not equals to UPDATED_ASSIGNABLE
        defaultNpaReportShouldBeFound("assignable.notEquals=" + UPDATED_ASSIGNABLE);
    }

    @Test
    @Transactional
    void getAllNpaReportsByAssignableIsInShouldWork() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where assignable in DEFAULT_ASSIGNABLE or UPDATED_ASSIGNABLE
        defaultNpaReportShouldBeFound("assignable.in=" + DEFAULT_ASSIGNABLE + "," + UPDATED_ASSIGNABLE);

        // Get all the npaReportList where assignable equals to UPDATED_ASSIGNABLE
        defaultNpaReportShouldNotBeFound("assignable.in=" + UPDATED_ASSIGNABLE);
    }

    @Test
    @Transactional
    void getAllNpaReportsByAssignableIsNullOrNotNull() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where assignable is not null
        defaultNpaReportShouldBeFound("assignable.specified=true");

        // Get all the npaReportList where assignable is null
        defaultNpaReportShouldNotBeFound("assignable.specified=false");
    }

    @Test
    @Transactional
    void getAllNpaReportsByAssignableContainsSomething() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where assignable contains DEFAULT_ASSIGNABLE
        defaultNpaReportShouldBeFound("assignable.contains=" + DEFAULT_ASSIGNABLE);

        // Get all the npaReportList where assignable contains UPDATED_ASSIGNABLE
        defaultNpaReportShouldNotBeFound("assignable.contains=" + UPDATED_ASSIGNABLE);
    }

    @Test
    @Transactional
    void getAllNpaReportsByAssignableNotContainsSomething() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where assignable does not contain DEFAULT_ASSIGNABLE
        defaultNpaReportShouldNotBeFound("assignable.doesNotContain=" + DEFAULT_ASSIGNABLE);

        // Get all the npaReportList where assignable does not contain UPDATED_ASSIGNABLE
        defaultNpaReportShouldBeFound("assignable.doesNotContain=" + UPDATED_ASSIGNABLE);
    }

    @Test
    @Transactional
    void getAllNpaReportsByExplanationIsEqualToSomething() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where explanation equals to DEFAULT_EXPLANATION
        defaultNpaReportShouldBeFound("explanation.equals=" + DEFAULT_EXPLANATION);

        // Get all the npaReportList where explanation equals to UPDATED_EXPLANATION
        defaultNpaReportShouldNotBeFound("explanation.equals=" + UPDATED_EXPLANATION);
    }

    @Test
    @Transactional
    void getAllNpaReportsByExplanationIsNotEqualToSomething() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where explanation not equals to DEFAULT_EXPLANATION
        defaultNpaReportShouldNotBeFound("explanation.notEquals=" + DEFAULT_EXPLANATION);

        // Get all the npaReportList where explanation not equals to UPDATED_EXPLANATION
        defaultNpaReportShouldBeFound("explanation.notEquals=" + UPDATED_EXPLANATION);
    }

    @Test
    @Transactional
    void getAllNpaReportsByExplanationIsInShouldWork() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where explanation in DEFAULT_EXPLANATION or UPDATED_EXPLANATION
        defaultNpaReportShouldBeFound("explanation.in=" + DEFAULT_EXPLANATION + "," + UPDATED_EXPLANATION);

        // Get all the npaReportList where explanation equals to UPDATED_EXPLANATION
        defaultNpaReportShouldNotBeFound("explanation.in=" + UPDATED_EXPLANATION);
    }

    @Test
    @Transactional
    void getAllNpaReportsByExplanationIsNullOrNotNull() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where explanation is not null
        defaultNpaReportShouldBeFound("explanation.specified=true");

        // Get all the npaReportList where explanation is null
        defaultNpaReportShouldNotBeFound("explanation.specified=false");
    }

    @Test
    @Transactional
    void getAllNpaReportsByExplanationContainsSomething() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where explanation contains DEFAULT_EXPLANATION
        defaultNpaReportShouldBeFound("explanation.contains=" + DEFAULT_EXPLANATION);

        // Get all the npaReportList where explanation contains UPDATED_EXPLANATION
        defaultNpaReportShouldNotBeFound("explanation.contains=" + UPDATED_EXPLANATION);
    }

    @Test
    @Transactional
    void getAllNpaReportsByExplanationNotContainsSomething() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where explanation does not contain DEFAULT_EXPLANATION
        defaultNpaReportShouldNotBeFound("explanation.doesNotContain=" + DEFAULT_EXPLANATION);

        // Get all the npaReportList where explanation does not contain UPDATED_EXPLANATION
        defaultNpaReportShouldBeFound("explanation.doesNotContain=" + UPDATED_EXPLANATION);
    }

    @Test
    @Transactional
    void getAllNpaReportsByReservedIsEqualToSomething() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where reserved equals to DEFAULT_RESERVED
        defaultNpaReportShouldBeFound("reserved.equals=" + DEFAULT_RESERVED);

        // Get all the npaReportList where reserved equals to UPDATED_RESERVED
        defaultNpaReportShouldNotBeFound("reserved.equals=" + UPDATED_RESERVED);
    }

    @Test
    @Transactional
    void getAllNpaReportsByReservedIsNotEqualToSomething() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where reserved not equals to DEFAULT_RESERVED
        defaultNpaReportShouldNotBeFound("reserved.notEquals=" + DEFAULT_RESERVED);

        // Get all the npaReportList where reserved not equals to UPDATED_RESERVED
        defaultNpaReportShouldBeFound("reserved.notEquals=" + UPDATED_RESERVED);
    }

    @Test
    @Transactional
    void getAllNpaReportsByReservedIsInShouldWork() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where reserved in DEFAULT_RESERVED or UPDATED_RESERVED
        defaultNpaReportShouldBeFound("reserved.in=" + DEFAULT_RESERVED + "," + UPDATED_RESERVED);

        // Get all the npaReportList where reserved equals to UPDATED_RESERVED
        defaultNpaReportShouldNotBeFound("reserved.in=" + UPDATED_RESERVED);
    }

    @Test
    @Transactional
    void getAllNpaReportsByReservedIsNullOrNotNull() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where reserved is not null
        defaultNpaReportShouldBeFound("reserved.specified=true");

        // Get all the npaReportList where reserved is null
        defaultNpaReportShouldNotBeFound("reserved.specified=false");
    }

    @Test
    @Transactional
    void getAllNpaReportsByReservedContainsSomething() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where reserved contains DEFAULT_RESERVED
        defaultNpaReportShouldBeFound("reserved.contains=" + DEFAULT_RESERVED);

        // Get all the npaReportList where reserved contains UPDATED_RESERVED
        defaultNpaReportShouldNotBeFound("reserved.contains=" + UPDATED_RESERVED);
    }

    @Test
    @Transactional
    void getAllNpaReportsByReservedNotContainsSomething() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where reserved does not contain DEFAULT_RESERVED
        defaultNpaReportShouldNotBeFound("reserved.doesNotContain=" + DEFAULT_RESERVED);

        // Get all the npaReportList where reserved does not contain UPDATED_RESERVED
        defaultNpaReportShouldBeFound("reserved.doesNotContain=" + UPDATED_RESERVED);
    }

    @Test
    @Transactional
    void getAllNpaReportsByAssignedIsEqualToSomething() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where assigned equals to DEFAULT_ASSIGNED
        defaultNpaReportShouldBeFound("assigned.equals=" + DEFAULT_ASSIGNED);

        // Get all the npaReportList where assigned equals to UPDATED_ASSIGNED
        defaultNpaReportShouldNotBeFound("assigned.equals=" + UPDATED_ASSIGNED);
    }

    @Test
    @Transactional
    void getAllNpaReportsByAssignedIsNotEqualToSomething() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where assigned not equals to DEFAULT_ASSIGNED
        defaultNpaReportShouldNotBeFound("assigned.notEquals=" + DEFAULT_ASSIGNED);

        // Get all the npaReportList where assigned not equals to UPDATED_ASSIGNED
        defaultNpaReportShouldBeFound("assigned.notEquals=" + UPDATED_ASSIGNED);
    }

    @Test
    @Transactional
    void getAllNpaReportsByAssignedIsInShouldWork() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where assigned in DEFAULT_ASSIGNED or UPDATED_ASSIGNED
        defaultNpaReportShouldBeFound("assigned.in=" + DEFAULT_ASSIGNED + "," + UPDATED_ASSIGNED);

        // Get all the npaReportList where assigned equals to UPDATED_ASSIGNED
        defaultNpaReportShouldNotBeFound("assigned.in=" + UPDATED_ASSIGNED);
    }

    @Test
    @Transactional
    void getAllNpaReportsByAssignedIsNullOrNotNull() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where assigned is not null
        defaultNpaReportShouldBeFound("assigned.specified=true");

        // Get all the npaReportList where assigned is null
        defaultNpaReportShouldNotBeFound("assigned.specified=false");
    }

    @Test
    @Transactional
    void getAllNpaReportsByAssignedContainsSomething() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where assigned contains DEFAULT_ASSIGNED
        defaultNpaReportShouldBeFound("assigned.contains=" + DEFAULT_ASSIGNED);

        // Get all the npaReportList where assigned contains UPDATED_ASSIGNED
        defaultNpaReportShouldNotBeFound("assigned.contains=" + UPDATED_ASSIGNED);
    }

    @Test
    @Transactional
    void getAllNpaReportsByAssignedNotContainsSomething() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where assigned does not contain DEFAULT_ASSIGNED
        defaultNpaReportShouldNotBeFound("assigned.doesNotContain=" + DEFAULT_ASSIGNED);

        // Get all the npaReportList where assigned does not contain UPDATED_ASSIGNED
        defaultNpaReportShouldBeFound("assigned.doesNotContain=" + UPDATED_ASSIGNED);
    }

    @Test
    @Transactional
    void getAllNpaReportsByAssignmentDateIsEqualToSomething() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where assignmentDate equals to DEFAULT_ASSIGNMENT_DATE
        defaultNpaReportShouldBeFound("assignmentDate.equals=" + DEFAULT_ASSIGNMENT_DATE);

        // Get all the npaReportList where assignmentDate equals to UPDATED_ASSIGNMENT_DATE
        defaultNpaReportShouldNotBeFound("assignmentDate.equals=" + UPDATED_ASSIGNMENT_DATE);
    }

    @Test
    @Transactional
    void getAllNpaReportsByAssignmentDateIsNotEqualToSomething() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where assignmentDate not equals to DEFAULT_ASSIGNMENT_DATE
        defaultNpaReportShouldNotBeFound("assignmentDate.notEquals=" + DEFAULT_ASSIGNMENT_DATE);

        // Get all the npaReportList where assignmentDate not equals to UPDATED_ASSIGNMENT_DATE
        defaultNpaReportShouldBeFound("assignmentDate.notEquals=" + UPDATED_ASSIGNMENT_DATE);
    }

    @Test
    @Transactional
    void getAllNpaReportsByAssignmentDateIsInShouldWork() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where assignmentDate in DEFAULT_ASSIGNMENT_DATE or UPDATED_ASSIGNMENT_DATE
        defaultNpaReportShouldBeFound("assignmentDate.in=" + DEFAULT_ASSIGNMENT_DATE + "," + UPDATED_ASSIGNMENT_DATE);

        // Get all the npaReportList where assignmentDate equals to UPDATED_ASSIGNMENT_DATE
        defaultNpaReportShouldNotBeFound("assignmentDate.in=" + UPDATED_ASSIGNMENT_DATE);
    }

    @Test
    @Transactional
    void getAllNpaReportsByAssignmentDateIsNullOrNotNull() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where assignmentDate is not null
        defaultNpaReportShouldBeFound("assignmentDate.specified=true");

        // Get all the npaReportList where assignmentDate is null
        defaultNpaReportShouldNotBeFound("assignmentDate.specified=false");
    }

    @Test
    @Transactional
    void getAllNpaReportsByAssignmentDateIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where assignmentDate is greater than or equal to DEFAULT_ASSIGNMENT_DATE
        defaultNpaReportShouldBeFound("assignmentDate.greaterThanOrEqual=" + DEFAULT_ASSIGNMENT_DATE);

        // Get all the npaReportList where assignmentDate is greater than or equal to UPDATED_ASSIGNMENT_DATE
        defaultNpaReportShouldNotBeFound("assignmentDate.greaterThanOrEqual=" + UPDATED_ASSIGNMENT_DATE);
    }

    @Test
    @Transactional
    void getAllNpaReportsByAssignmentDateIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where assignmentDate is less than or equal to DEFAULT_ASSIGNMENT_DATE
        defaultNpaReportShouldBeFound("assignmentDate.lessThanOrEqual=" + DEFAULT_ASSIGNMENT_DATE);

        // Get all the npaReportList where assignmentDate is less than or equal to SMALLER_ASSIGNMENT_DATE
        defaultNpaReportShouldNotBeFound("assignmentDate.lessThanOrEqual=" + SMALLER_ASSIGNMENT_DATE);
    }

    @Test
    @Transactional
    void getAllNpaReportsByAssignmentDateIsLessThanSomething() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where assignmentDate is less than DEFAULT_ASSIGNMENT_DATE
        defaultNpaReportShouldNotBeFound("assignmentDate.lessThan=" + DEFAULT_ASSIGNMENT_DATE);

        // Get all the npaReportList where assignmentDate is less than UPDATED_ASSIGNMENT_DATE
        defaultNpaReportShouldBeFound("assignmentDate.lessThan=" + UPDATED_ASSIGNMENT_DATE);
    }

    @Test
    @Transactional
    void getAllNpaReportsByAssignmentDateIsGreaterThanSomething() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where assignmentDate is greater than DEFAULT_ASSIGNMENT_DATE
        defaultNpaReportShouldNotBeFound("assignmentDate.greaterThan=" + DEFAULT_ASSIGNMENT_DATE);

        // Get all the npaReportList where assignmentDate is greater than SMALLER_ASSIGNMENT_DATE
        defaultNpaReportShouldBeFound("assignmentDate.greaterThan=" + SMALLER_ASSIGNMENT_DATE);
    }

    @Test
    @Transactional
    void getAllNpaReportsByUseTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where useType equals to DEFAULT_USE_TYPE
        defaultNpaReportShouldBeFound("useType.equals=" + DEFAULT_USE_TYPE);

        // Get all the npaReportList where useType equals to UPDATED_USE_TYPE
        defaultNpaReportShouldNotBeFound("useType.equals=" + UPDATED_USE_TYPE);
    }

    @Test
    @Transactional
    void getAllNpaReportsByUseTypeIsNotEqualToSomething() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where useType not equals to DEFAULT_USE_TYPE
        defaultNpaReportShouldNotBeFound("useType.notEquals=" + DEFAULT_USE_TYPE);

        // Get all the npaReportList where useType not equals to UPDATED_USE_TYPE
        defaultNpaReportShouldBeFound("useType.notEquals=" + UPDATED_USE_TYPE);
    }

    @Test
    @Transactional
    void getAllNpaReportsByUseTypeIsInShouldWork() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where useType in DEFAULT_USE_TYPE or UPDATED_USE_TYPE
        defaultNpaReportShouldBeFound("useType.in=" + DEFAULT_USE_TYPE + "," + UPDATED_USE_TYPE);

        // Get all the npaReportList where useType equals to UPDATED_USE_TYPE
        defaultNpaReportShouldNotBeFound("useType.in=" + UPDATED_USE_TYPE);
    }

    @Test
    @Transactional
    void getAllNpaReportsByUseTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where useType is not null
        defaultNpaReportShouldBeFound("useType.specified=true");

        // Get all the npaReportList where useType is null
        defaultNpaReportShouldNotBeFound("useType.specified=false");
    }

    @Test
    @Transactional
    void getAllNpaReportsByUseTypeContainsSomething() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where useType contains DEFAULT_USE_TYPE
        defaultNpaReportShouldBeFound("useType.contains=" + DEFAULT_USE_TYPE);

        // Get all the npaReportList where useType contains UPDATED_USE_TYPE
        defaultNpaReportShouldNotBeFound("useType.contains=" + UPDATED_USE_TYPE);
    }

    @Test
    @Transactional
    void getAllNpaReportsByUseTypeNotContainsSomething() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where useType does not contain DEFAULT_USE_TYPE
        defaultNpaReportShouldNotBeFound("useType.doesNotContain=" + DEFAULT_USE_TYPE);

        // Get all the npaReportList where useType does not contain UPDATED_USE_TYPE
        defaultNpaReportShouldBeFound("useType.doesNotContain=" + UPDATED_USE_TYPE);
    }

    @Test
    @Transactional
    void getAllNpaReportsByLocationIsEqualToSomething() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where location equals to DEFAULT_LOCATION
        defaultNpaReportShouldBeFound("location.equals=" + DEFAULT_LOCATION);

        // Get all the npaReportList where location equals to UPDATED_LOCATION
        defaultNpaReportShouldNotBeFound("location.equals=" + UPDATED_LOCATION);
    }

    @Test
    @Transactional
    void getAllNpaReportsByLocationIsNotEqualToSomething() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where location not equals to DEFAULT_LOCATION
        defaultNpaReportShouldNotBeFound("location.notEquals=" + DEFAULT_LOCATION);

        // Get all the npaReportList where location not equals to UPDATED_LOCATION
        defaultNpaReportShouldBeFound("location.notEquals=" + UPDATED_LOCATION);
    }

    @Test
    @Transactional
    void getAllNpaReportsByLocationIsInShouldWork() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where location in DEFAULT_LOCATION or UPDATED_LOCATION
        defaultNpaReportShouldBeFound("location.in=" + DEFAULT_LOCATION + "," + UPDATED_LOCATION);

        // Get all the npaReportList where location equals to UPDATED_LOCATION
        defaultNpaReportShouldNotBeFound("location.in=" + UPDATED_LOCATION);
    }

    @Test
    @Transactional
    void getAllNpaReportsByLocationIsNullOrNotNull() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where location is not null
        defaultNpaReportShouldBeFound("location.specified=true");

        // Get all the npaReportList where location is null
        defaultNpaReportShouldNotBeFound("location.specified=false");
    }

    @Test
    @Transactional
    void getAllNpaReportsByLocationContainsSomething() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where location contains DEFAULT_LOCATION
        defaultNpaReportShouldBeFound("location.contains=" + DEFAULT_LOCATION);

        // Get all the npaReportList where location contains UPDATED_LOCATION
        defaultNpaReportShouldNotBeFound("location.contains=" + UPDATED_LOCATION);
    }

    @Test
    @Transactional
    void getAllNpaReportsByLocationNotContainsSomething() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where location does not contain DEFAULT_LOCATION
        defaultNpaReportShouldNotBeFound("location.doesNotContain=" + DEFAULT_LOCATION);

        // Get all the npaReportList where location does not contain UPDATED_LOCATION
        defaultNpaReportShouldBeFound("location.doesNotContain=" + UPDATED_LOCATION);
    }

    @Test
    @Transactional
    void getAllNpaReportsByCountryIsEqualToSomething() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where country equals to DEFAULT_COUNTRY
        defaultNpaReportShouldBeFound("country.equals=" + DEFAULT_COUNTRY);

        // Get all the npaReportList where country equals to UPDATED_COUNTRY
        defaultNpaReportShouldNotBeFound("country.equals=" + UPDATED_COUNTRY);
    }

    @Test
    @Transactional
    void getAllNpaReportsByCountryIsNotEqualToSomething() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where country not equals to DEFAULT_COUNTRY
        defaultNpaReportShouldNotBeFound("country.notEquals=" + DEFAULT_COUNTRY);

        // Get all the npaReportList where country not equals to UPDATED_COUNTRY
        defaultNpaReportShouldBeFound("country.notEquals=" + UPDATED_COUNTRY);
    }

    @Test
    @Transactional
    void getAllNpaReportsByCountryIsInShouldWork() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where country in DEFAULT_COUNTRY or UPDATED_COUNTRY
        defaultNpaReportShouldBeFound("country.in=" + DEFAULT_COUNTRY + "," + UPDATED_COUNTRY);

        // Get all the npaReportList where country equals to UPDATED_COUNTRY
        defaultNpaReportShouldNotBeFound("country.in=" + UPDATED_COUNTRY);
    }

    @Test
    @Transactional
    void getAllNpaReportsByCountryIsNullOrNotNull() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where country is not null
        defaultNpaReportShouldBeFound("country.specified=true");

        // Get all the npaReportList where country is null
        defaultNpaReportShouldNotBeFound("country.specified=false");
    }

    @Test
    @Transactional
    void getAllNpaReportsByCountryContainsSomething() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where country contains DEFAULT_COUNTRY
        defaultNpaReportShouldBeFound("country.contains=" + DEFAULT_COUNTRY);

        // Get all the npaReportList where country contains UPDATED_COUNTRY
        defaultNpaReportShouldNotBeFound("country.contains=" + UPDATED_COUNTRY);
    }

    @Test
    @Transactional
    void getAllNpaReportsByCountryNotContainsSomething() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where country does not contain DEFAULT_COUNTRY
        defaultNpaReportShouldNotBeFound("country.doesNotContain=" + DEFAULT_COUNTRY);

        // Get all the npaReportList where country does not contain UPDATED_COUNTRY
        defaultNpaReportShouldBeFound("country.doesNotContain=" + UPDATED_COUNTRY);
    }

    @Test
    @Transactional
    void getAllNpaReportsByInServiceIsEqualToSomething() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where inService equals to DEFAULT_IN_SERVICE
        defaultNpaReportShouldBeFound("inService.equals=" + DEFAULT_IN_SERVICE);

        // Get all the npaReportList where inService equals to UPDATED_IN_SERVICE
        defaultNpaReportShouldNotBeFound("inService.equals=" + UPDATED_IN_SERVICE);
    }

    @Test
    @Transactional
    void getAllNpaReportsByInServiceIsNotEqualToSomething() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where inService not equals to DEFAULT_IN_SERVICE
        defaultNpaReportShouldNotBeFound("inService.notEquals=" + DEFAULT_IN_SERVICE);

        // Get all the npaReportList where inService not equals to UPDATED_IN_SERVICE
        defaultNpaReportShouldBeFound("inService.notEquals=" + UPDATED_IN_SERVICE);
    }

    @Test
    @Transactional
    void getAllNpaReportsByInServiceIsInShouldWork() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where inService in DEFAULT_IN_SERVICE or UPDATED_IN_SERVICE
        defaultNpaReportShouldBeFound("inService.in=" + DEFAULT_IN_SERVICE + "," + UPDATED_IN_SERVICE);

        // Get all the npaReportList where inService equals to UPDATED_IN_SERVICE
        defaultNpaReportShouldNotBeFound("inService.in=" + UPDATED_IN_SERVICE);
    }

    @Test
    @Transactional
    void getAllNpaReportsByInServiceIsNullOrNotNull() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where inService is not null
        defaultNpaReportShouldBeFound("inService.specified=true");

        // Get all the npaReportList where inService is null
        defaultNpaReportShouldNotBeFound("inService.specified=false");
    }

    @Test
    @Transactional
    void getAllNpaReportsByInServiceContainsSomething() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where inService contains DEFAULT_IN_SERVICE
        defaultNpaReportShouldBeFound("inService.contains=" + DEFAULT_IN_SERVICE);

        // Get all the npaReportList where inService contains UPDATED_IN_SERVICE
        defaultNpaReportShouldNotBeFound("inService.contains=" + UPDATED_IN_SERVICE);
    }

    @Test
    @Transactional
    void getAllNpaReportsByInServiceNotContainsSomething() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where inService does not contain DEFAULT_IN_SERVICE
        defaultNpaReportShouldNotBeFound("inService.doesNotContain=" + DEFAULT_IN_SERVICE);

        // Get all the npaReportList where inService does not contain UPDATED_IN_SERVICE
        defaultNpaReportShouldBeFound("inService.doesNotContain=" + UPDATED_IN_SERVICE);
    }

    @Test
    @Transactional
    void getAllNpaReportsByInServiceDateIsEqualToSomething() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where inServiceDate equals to DEFAULT_IN_SERVICE_DATE
        defaultNpaReportShouldBeFound("inServiceDate.equals=" + DEFAULT_IN_SERVICE_DATE);

        // Get all the npaReportList where inServiceDate equals to UPDATED_IN_SERVICE_DATE
        defaultNpaReportShouldNotBeFound("inServiceDate.equals=" + UPDATED_IN_SERVICE_DATE);
    }

    @Test
    @Transactional
    void getAllNpaReportsByInServiceDateIsNotEqualToSomething() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where inServiceDate not equals to DEFAULT_IN_SERVICE_DATE
        defaultNpaReportShouldNotBeFound("inServiceDate.notEquals=" + DEFAULT_IN_SERVICE_DATE);

        // Get all the npaReportList where inServiceDate not equals to UPDATED_IN_SERVICE_DATE
        defaultNpaReportShouldBeFound("inServiceDate.notEquals=" + UPDATED_IN_SERVICE_DATE);
    }

    @Test
    @Transactional
    void getAllNpaReportsByInServiceDateIsInShouldWork() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where inServiceDate in DEFAULT_IN_SERVICE_DATE or UPDATED_IN_SERVICE_DATE
        defaultNpaReportShouldBeFound("inServiceDate.in=" + DEFAULT_IN_SERVICE_DATE + "," + UPDATED_IN_SERVICE_DATE);

        // Get all the npaReportList where inServiceDate equals to UPDATED_IN_SERVICE_DATE
        defaultNpaReportShouldNotBeFound("inServiceDate.in=" + UPDATED_IN_SERVICE_DATE);
    }

    @Test
    @Transactional
    void getAllNpaReportsByInServiceDateIsNullOrNotNull() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where inServiceDate is not null
        defaultNpaReportShouldBeFound("inServiceDate.specified=true");

        // Get all the npaReportList where inServiceDate is null
        defaultNpaReportShouldNotBeFound("inServiceDate.specified=false");
    }

    @Test
    @Transactional
    void getAllNpaReportsByInServiceDateIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where inServiceDate is greater than or equal to DEFAULT_IN_SERVICE_DATE
        defaultNpaReportShouldBeFound("inServiceDate.greaterThanOrEqual=" + DEFAULT_IN_SERVICE_DATE);

        // Get all the npaReportList where inServiceDate is greater than or equal to UPDATED_IN_SERVICE_DATE
        defaultNpaReportShouldNotBeFound("inServiceDate.greaterThanOrEqual=" + UPDATED_IN_SERVICE_DATE);
    }

    @Test
    @Transactional
    void getAllNpaReportsByInServiceDateIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where inServiceDate is less than or equal to DEFAULT_IN_SERVICE_DATE
        defaultNpaReportShouldBeFound("inServiceDate.lessThanOrEqual=" + DEFAULT_IN_SERVICE_DATE);

        // Get all the npaReportList where inServiceDate is less than or equal to SMALLER_IN_SERVICE_DATE
        defaultNpaReportShouldNotBeFound("inServiceDate.lessThanOrEqual=" + SMALLER_IN_SERVICE_DATE);
    }

    @Test
    @Transactional
    void getAllNpaReportsByInServiceDateIsLessThanSomething() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where inServiceDate is less than DEFAULT_IN_SERVICE_DATE
        defaultNpaReportShouldNotBeFound("inServiceDate.lessThan=" + DEFAULT_IN_SERVICE_DATE);

        // Get all the npaReportList where inServiceDate is less than UPDATED_IN_SERVICE_DATE
        defaultNpaReportShouldBeFound("inServiceDate.lessThan=" + UPDATED_IN_SERVICE_DATE);
    }

    @Test
    @Transactional
    void getAllNpaReportsByInServiceDateIsGreaterThanSomething() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where inServiceDate is greater than DEFAULT_IN_SERVICE_DATE
        defaultNpaReportShouldNotBeFound("inServiceDate.greaterThan=" + DEFAULT_IN_SERVICE_DATE);

        // Get all the npaReportList where inServiceDate is greater than SMALLER_IN_SERVICE_DATE
        defaultNpaReportShouldBeFound("inServiceDate.greaterThan=" + SMALLER_IN_SERVICE_DATE);
    }

    @Test
    @Transactional
    void getAllNpaReportsByStatusIsEqualToSomething() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where status equals to DEFAULT_STATUS
        defaultNpaReportShouldBeFound("status.equals=" + DEFAULT_STATUS);

        // Get all the npaReportList where status equals to UPDATED_STATUS
        defaultNpaReportShouldNotBeFound("status.equals=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    void getAllNpaReportsByStatusIsNotEqualToSomething() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where status not equals to DEFAULT_STATUS
        defaultNpaReportShouldNotBeFound("status.notEquals=" + DEFAULT_STATUS);

        // Get all the npaReportList where status not equals to UPDATED_STATUS
        defaultNpaReportShouldBeFound("status.notEquals=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    void getAllNpaReportsByStatusIsInShouldWork() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where status in DEFAULT_STATUS or UPDATED_STATUS
        defaultNpaReportShouldBeFound("status.in=" + DEFAULT_STATUS + "," + UPDATED_STATUS);

        // Get all the npaReportList where status equals to UPDATED_STATUS
        defaultNpaReportShouldNotBeFound("status.in=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    void getAllNpaReportsByStatusIsNullOrNotNull() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where status is not null
        defaultNpaReportShouldBeFound("status.specified=true");

        // Get all the npaReportList where status is null
        defaultNpaReportShouldNotBeFound("status.specified=false");
    }

    @Test
    @Transactional
    void getAllNpaReportsByStatusContainsSomething() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where status contains DEFAULT_STATUS
        defaultNpaReportShouldBeFound("status.contains=" + DEFAULT_STATUS);

        // Get all the npaReportList where status contains UPDATED_STATUS
        defaultNpaReportShouldNotBeFound("status.contains=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    void getAllNpaReportsByStatusNotContainsSomething() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where status does not contain DEFAULT_STATUS
        defaultNpaReportShouldNotBeFound("status.doesNotContain=" + DEFAULT_STATUS);

        // Get all the npaReportList where status does not contain UPDATED_STATUS
        defaultNpaReportShouldBeFound("status.doesNotContain=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    void getAllNpaReportsByPlanningLettersIsEqualToSomething() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where planningLetters equals to DEFAULT_PLANNING_LETTERS
        defaultNpaReportShouldBeFound("planningLetters.equals=" + DEFAULT_PLANNING_LETTERS);

        // Get all the npaReportList where planningLetters equals to UPDATED_PLANNING_LETTERS
        defaultNpaReportShouldNotBeFound("planningLetters.equals=" + UPDATED_PLANNING_LETTERS);
    }

    @Test
    @Transactional
    void getAllNpaReportsByPlanningLettersIsNotEqualToSomething() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where planningLetters not equals to DEFAULT_PLANNING_LETTERS
        defaultNpaReportShouldNotBeFound("planningLetters.notEquals=" + DEFAULT_PLANNING_LETTERS);

        // Get all the npaReportList where planningLetters not equals to UPDATED_PLANNING_LETTERS
        defaultNpaReportShouldBeFound("planningLetters.notEquals=" + UPDATED_PLANNING_LETTERS);
    }

    @Test
    @Transactional
    void getAllNpaReportsByPlanningLettersIsInShouldWork() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where planningLetters in DEFAULT_PLANNING_LETTERS or UPDATED_PLANNING_LETTERS
        defaultNpaReportShouldBeFound("planningLetters.in=" + DEFAULT_PLANNING_LETTERS + "," + UPDATED_PLANNING_LETTERS);

        // Get all the npaReportList where planningLetters equals to UPDATED_PLANNING_LETTERS
        defaultNpaReportShouldNotBeFound("planningLetters.in=" + UPDATED_PLANNING_LETTERS);
    }

    @Test
    @Transactional
    void getAllNpaReportsByPlanningLettersIsNullOrNotNull() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where planningLetters is not null
        defaultNpaReportShouldBeFound("planningLetters.specified=true");

        // Get all the npaReportList where planningLetters is null
        defaultNpaReportShouldNotBeFound("planningLetters.specified=false");
    }

    @Test
    @Transactional
    void getAllNpaReportsByPlanningLettersContainsSomething() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where planningLetters contains DEFAULT_PLANNING_LETTERS
        defaultNpaReportShouldBeFound("planningLetters.contains=" + DEFAULT_PLANNING_LETTERS);

        // Get all the npaReportList where planningLetters contains UPDATED_PLANNING_LETTERS
        defaultNpaReportShouldNotBeFound("planningLetters.contains=" + UPDATED_PLANNING_LETTERS);
    }

    @Test
    @Transactional
    void getAllNpaReportsByPlanningLettersNotContainsSomething() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where planningLetters does not contain DEFAULT_PLANNING_LETTERS
        defaultNpaReportShouldNotBeFound("planningLetters.doesNotContain=" + DEFAULT_PLANNING_LETTERS);

        // Get all the npaReportList where planningLetters does not contain UPDATED_PLANNING_LETTERS
        defaultNpaReportShouldBeFound("planningLetters.doesNotContain=" + UPDATED_PLANNING_LETTERS);
    }

    @Test
    @Transactional
    void getAllNpaReportsByNotesIsEqualToSomething() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where notes equals to DEFAULT_NOTES
        defaultNpaReportShouldBeFound("notes.equals=" + DEFAULT_NOTES);

        // Get all the npaReportList where notes equals to UPDATED_NOTES
        defaultNpaReportShouldNotBeFound("notes.equals=" + UPDATED_NOTES);
    }

    @Test
    @Transactional
    void getAllNpaReportsByNotesIsNotEqualToSomething() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where notes not equals to DEFAULT_NOTES
        defaultNpaReportShouldNotBeFound("notes.notEquals=" + DEFAULT_NOTES);

        // Get all the npaReportList where notes not equals to UPDATED_NOTES
        defaultNpaReportShouldBeFound("notes.notEquals=" + UPDATED_NOTES);
    }

    @Test
    @Transactional
    void getAllNpaReportsByNotesIsInShouldWork() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where notes in DEFAULT_NOTES or UPDATED_NOTES
        defaultNpaReportShouldBeFound("notes.in=" + DEFAULT_NOTES + "," + UPDATED_NOTES);

        // Get all the npaReportList where notes equals to UPDATED_NOTES
        defaultNpaReportShouldNotBeFound("notes.in=" + UPDATED_NOTES);
    }

    @Test
    @Transactional
    void getAllNpaReportsByNotesIsNullOrNotNull() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where notes is not null
        defaultNpaReportShouldBeFound("notes.specified=true");

        // Get all the npaReportList where notes is null
        defaultNpaReportShouldNotBeFound("notes.specified=false");
    }

    @Test
    @Transactional
    void getAllNpaReportsByNotesContainsSomething() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where notes contains DEFAULT_NOTES
        defaultNpaReportShouldBeFound("notes.contains=" + DEFAULT_NOTES);

        // Get all the npaReportList where notes contains UPDATED_NOTES
        defaultNpaReportShouldNotBeFound("notes.contains=" + UPDATED_NOTES);
    }

    @Test
    @Transactional
    void getAllNpaReportsByNotesNotContainsSomething() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where notes does not contain DEFAULT_NOTES
        defaultNpaReportShouldNotBeFound("notes.doesNotContain=" + DEFAULT_NOTES);

        // Get all the npaReportList where notes does not contain UPDATED_NOTES
        defaultNpaReportShouldBeFound("notes.doesNotContain=" + UPDATED_NOTES);
    }

    @Test
    @Transactional
    void getAllNpaReportsByOverlayIsEqualToSomething() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where overlay equals to DEFAULT_OVERLAY
        defaultNpaReportShouldBeFound("overlay.equals=" + DEFAULT_OVERLAY);

        // Get all the npaReportList where overlay equals to UPDATED_OVERLAY
        defaultNpaReportShouldNotBeFound("overlay.equals=" + UPDATED_OVERLAY);
    }

    @Test
    @Transactional
    void getAllNpaReportsByOverlayIsNotEqualToSomething() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where overlay not equals to DEFAULT_OVERLAY
        defaultNpaReportShouldNotBeFound("overlay.notEquals=" + DEFAULT_OVERLAY);

        // Get all the npaReportList where overlay not equals to UPDATED_OVERLAY
        defaultNpaReportShouldBeFound("overlay.notEquals=" + UPDATED_OVERLAY);
    }

    @Test
    @Transactional
    void getAllNpaReportsByOverlayIsInShouldWork() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where overlay in DEFAULT_OVERLAY or UPDATED_OVERLAY
        defaultNpaReportShouldBeFound("overlay.in=" + DEFAULT_OVERLAY + "," + UPDATED_OVERLAY);

        // Get all the npaReportList where overlay equals to UPDATED_OVERLAY
        defaultNpaReportShouldNotBeFound("overlay.in=" + UPDATED_OVERLAY);
    }

    @Test
    @Transactional
    void getAllNpaReportsByOverlayIsNullOrNotNull() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where overlay is not null
        defaultNpaReportShouldBeFound("overlay.specified=true");

        // Get all the npaReportList where overlay is null
        defaultNpaReportShouldNotBeFound("overlay.specified=false");
    }

    @Test
    @Transactional
    void getAllNpaReportsByOverlayContainsSomething() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where overlay contains DEFAULT_OVERLAY
        defaultNpaReportShouldBeFound("overlay.contains=" + DEFAULT_OVERLAY);

        // Get all the npaReportList where overlay contains UPDATED_OVERLAY
        defaultNpaReportShouldNotBeFound("overlay.contains=" + UPDATED_OVERLAY);
    }

    @Test
    @Transactional
    void getAllNpaReportsByOverlayNotContainsSomething() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where overlay does not contain DEFAULT_OVERLAY
        defaultNpaReportShouldNotBeFound("overlay.doesNotContain=" + DEFAULT_OVERLAY);

        // Get all the npaReportList where overlay does not contain UPDATED_OVERLAY
        defaultNpaReportShouldBeFound("overlay.doesNotContain=" + UPDATED_OVERLAY);
    }

    @Test
    @Transactional
    void getAllNpaReportsByOverlayComplexIsEqualToSomething() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where overlayComplex equals to DEFAULT_OVERLAY_COMPLEX
        defaultNpaReportShouldBeFound("overlayComplex.equals=" + DEFAULT_OVERLAY_COMPLEX);

        // Get all the npaReportList where overlayComplex equals to UPDATED_OVERLAY_COMPLEX
        defaultNpaReportShouldNotBeFound("overlayComplex.equals=" + UPDATED_OVERLAY_COMPLEX);
    }

    @Test
    @Transactional
    void getAllNpaReportsByOverlayComplexIsNotEqualToSomething() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where overlayComplex not equals to DEFAULT_OVERLAY_COMPLEX
        defaultNpaReportShouldNotBeFound("overlayComplex.notEquals=" + DEFAULT_OVERLAY_COMPLEX);

        // Get all the npaReportList where overlayComplex not equals to UPDATED_OVERLAY_COMPLEX
        defaultNpaReportShouldBeFound("overlayComplex.notEquals=" + UPDATED_OVERLAY_COMPLEX);
    }

    @Test
    @Transactional
    void getAllNpaReportsByOverlayComplexIsInShouldWork() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where overlayComplex in DEFAULT_OVERLAY_COMPLEX or UPDATED_OVERLAY_COMPLEX
        defaultNpaReportShouldBeFound("overlayComplex.in=" + DEFAULT_OVERLAY_COMPLEX + "," + UPDATED_OVERLAY_COMPLEX);

        // Get all the npaReportList where overlayComplex equals to UPDATED_OVERLAY_COMPLEX
        defaultNpaReportShouldNotBeFound("overlayComplex.in=" + UPDATED_OVERLAY_COMPLEX);
    }

    @Test
    @Transactional
    void getAllNpaReportsByOverlayComplexIsNullOrNotNull() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where overlayComplex is not null
        defaultNpaReportShouldBeFound("overlayComplex.specified=true");

        // Get all the npaReportList where overlayComplex is null
        defaultNpaReportShouldNotBeFound("overlayComplex.specified=false");
    }

    @Test
    @Transactional
    void getAllNpaReportsByOverlayComplexContainsSomething() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where overlayComplex contains DEFAULT_OVERLAY_COMPLEX
        defaultNpaReportShouldBeFound("overlayComplex.contains=" + DEFAULT_OVERLAY_COMPLEX);

        // Get all the npaReportList where overlayComplex contains UPDATED_OVERLAY_COMPLEX
        defaultNpaReportShouldNotBeFound("overlayComplex.contains=" + UPDATED_OVERLAY_COMPLEX);
    }

    @Test
    @Transactional
    void getAllNpaReportsByOverlayComplexNotContainsSomething() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where overlayComplex does not contain DEFAULT_OVERLAY_COMPLEX
        defaultNpaReportShouldNotBeFound("overlayComplex.doesNotContain=" + DEFAULT_OVERLAY_COMPLEX);

        // Get all the npaReportList where overlayComplex does not contain UPDATED_OVERLAY_COMPLEX
        defaultNpaReportShouldBeFound("overlayComplex.doesNotContain=" + UPDATED_OVERLAY_COMPLEX);
    }

    @Test
    @Transactional
    void getAllNpaReportsByParentNpaIdIsEqualToSomething() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where parentNpaId equals to DEFAULT_PARENT_NPA_ID
        defaultNpaReportShouldBeFound("parentNpaId.equals=" + DEFAULT_PARENT_NPA_ID);

        // Get all the npaReportList where parentNpaId equals to UPDATED_PARENT_NPA_ID
        defaultNpaReportShouldNotBeFound("parentNpaId.equals=" + UPDATED_PARENT_NPA_ID);
    }

    @Test
    @Transactional
    void getAllNpaReportsByParentNpaIdIsNotEqualToSomething() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where parentNpaId not equals to DEFAULT_PARENT_NPA_ID
        defaultNpaReportShouldNotBeFound("parentNpaId.notEquals=" + DEFAULT_PARENT_NPA_ID);

        // Get all the npaReportList where parentNpaId not equals to UPDATED_PARENT_NPA_ID
        defaultNpaReportShouldBeFound("parentNpaId.notEquals=" + UPDATED_PARENT_NPA_ID);
    }

    @Test
    @Transactional
    void getAllNpaReportsByParentNpaIdIsInShouldWork() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where parentNpaId in DEFAULT_PARENT_NPA_ID or UPDATED_PARENT_NPA_ID
        defaultNpaReportShouldBeFound("parentNpaId.in=" + DEFAULT_PARENT_NPA_ID + "," + UPDATED_PARENT_NPA_ID);

        // Get all the npaReportList where parentNpaId equals to UPDATED_PARENT_NPA_ID
        defaultNpaReportShouldNotBeFound("parentNpaId.in=" + UPDATED_PARENT_NPA_ID);
    }

    @Test
    @Transactional
    void getAllNpaReportsByParentNpaIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where parentNpaId is not null
        defaultNpaReportShouldBeFound("parentNpaId.specified=true");

        // Get all the npaReportList where parentNpaId is null
        defaultNpaReportShouldNotBeFound("parentNpaId.specified=false");
    }

    @Test
    @Transactional
    void getAllNpaReportsByParentNpaIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where parentNpaId is greater than or equal to DEFAULT_PARENT_NPA_ID
        defaultNpaReportShouldBeFound("parentNpaId.greaterThanOrEqual=" + DEFAULT_PARENT_NPA_ID);

        // Get all the npaReportList where parentNpaId is greater than or equal to UPDATED_PARENT_NPA_ID
        defaultNpaReportShouldNotBeFound("parentNpaId.greaterThanOrEqual=" + UPDATED_PARENT_NPA_ID);
    }

    @Test
    @Transactional
    void getAllNpaReportsByParentNpaIdIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where parentNpaId is less than or equal to DEFAULT_PARENT_NPA_ID
        defaultNpaReportShouldBeFound("parentNpaId.lessThanOrEqual=" + DEFAULT_PARENT_NPA_ID);

        // Get all the npaReportList where parentNpaId is less than or equal to SMALLER_PARENT_NPA_ID
        defaultNpaReportShouldNotBeFound("parentNpaId.lessThanOrEqual=" + SMALLER_PARENT_NPA_ID);
    }

    @Test
    @Transactional
    void getAllNpaReportsByParentNpaIdIsLessThanSomething() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where parentNpaId is less than DEFAULT_PARENT_NPA_ID
        defaultNpaReportShouldNotBeFound("parentNpaId.lessThan=" + DEFAULT_PARENT_NPA_ID);

        // Get all the npaReportList where parentNpaId is less than UPDATED_PARENT_NPA_ID
        defaultNpaReportShouldBeFound("parentNpaId.lessThan=" + UPDATED_PARENT_NPA_ID);
    }

    @Test
    @Transactional
    void getAllNpaReportsByParentNpaIdIsGreaterThanSomething() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where parentNpaId is greater than DEFAULT_PARENT_NPA_ID
        defaultNpaReportShouldNotBeFound("parentNpaId.greaterThan=" + DEFAULT_PARENT_NPA_ID);

        // Get all the npaReportList where parentNpaId is greater than SMALLER_PARENT_NPA_ID
        defaultNpaReportShouldBeFound("parentNpaId.greaterThan=" + SMALLER_PARENT_NPA_ID);
    }

    @Test
    @Transactional
    void getAllNpaReportsByServiceIsEqualToSomething() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where service equals to DEFAULT_SERVICE
        defaultNpaReportShouldBeFound("service.equals=" + DEFAULT_SERVICE);

        // Get all the npaReportList where service equals to UPDATED_SERVICE
        defaultNpaReportShouldNotBeFound("service.equals=" + UPDATED_SERVICE);
    }

    @Test
    @Transactional
    void getAllNpaReportsByServiceIsNotEqualToSomething() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where service not equals to DEFAULT_SERVICE
        defaultNpaReportShouldNotBeFound("service.notEquals=" + DEFAULT_SERVICE);

        // Get all the npaReportList where service not equals to UPDATED_SERVICE
        defaultNpaReportShouldBeFound("service.notEquals=" + UPDATED_SERVICE);
    }

    @Test
    @Transactional
    void getAllNpaReportsByServiceIsInShouldWork() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where service in DEFAULT_SERVICE or UPDATED_SERVICE
        defaultNpaReportShouldBeFound("service.in=" + DEFAULT_SERVICE + "," + UPDATED_SERVICE);

        // Get all the npaReportList where service equals to UPDATED_SERVICE
        defaultNpaReportShouldNotBeFound("service.in=" + UPDATED_SERVICE);
    }

    @Test
    @Transactional
    void getAllNpaReportsByServiceIsNullOrNotNull() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where service is not null
        defaultNpaReportShouldBeFound("service.specified=true");

        // Get all the npaReportList where service is null
        defaultNpaReportShouldNotBeFound("service.specified=false");
    }

    @Test
    @Transactional
    void getAllNpaReportsByServiceContainsSomething() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where service contains DEFAULT_SERVICE
        defaultNpaReportShouldBeFound("service.contains=" + DEFAULT_SERVICE);

        // Get all the npaReportList where service contains UPDATED_SERVICE
        defaultNpaReportShouldNotBeFound("service.contains=" + UPDATED_SERVICE);
    }

    @Test
    @Transactional
    void getAllNpaReportsByServiceNotContainsSomething() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where service does not contain DEFAULT_SERVICE
        defaultNpaReportShouldNotBeFound("service.doesNotContain=" + DEFAULT_SERVICE);

        // Get all the npaReportList where service does not contain UPDATED_SERVICE
        defaultNpaReportShouldBeFound("service.doesNotContain=" + UPDATED_SERVICE);
    }

    @Test
    @Transactional
    void getAllNpaReportsByTimeZoneIsEqualToSomething() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where timeZone equals to DEFAULT_TIME_ZONE
        defaultNpaReportShouldBeFound("timeZone.equals=" + DEFAULT_TIME_ZONE);

        // Get all the npaReportList where timeZone equals to UPDATED_TIME_ZONE
        defaultNpaReportShouldNotBeFound("timeZone.equals=" + UPDATED_TIME_ZONE);
    }

    @Test
    @Transactional
    void getAllNpaReportsByTimeZoneIsNotEqualToSomething() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where timeZone not equals to DEFAULT_TIME_ZONE
        defaultNpaReportShouldNotBeFound("timeZone.notEquals=" + DEFAULT_TIME_ZONE);

        // Get all the npaReportList where timeZone not equals to UPDATED_TIME_ZONE
        defaultNpaReportShouldBeFound("timeZone.notEquals=" + UPDATED_TIME_ZONE);
    }

    @Test
    @Transactional
    void getAllNpaReportsByTimeZoneIsInShouldWork() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where timeZone in DEFAULT_TIME_ZONE or UPDATED_TIME_ZONE
        defaultNpaReportShouldBeFound("timeZone.in=" + DEFAULT_TIME_ZONE + "," + UPDATED_TIME_ZONE);

        // Get all the npaReportList where timeZone equals to UPDATED_TIME_ZONE
        defaultNpaReportShouldNotBeFound("timeZone.in=" + UPDATED_TIME_ZONE);
    }

    @Test
    @Transactional
    void getAllNpaReportsByTimeZoneIsNullOrNotNull() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where timeZone is not null
        defaultNpaReportShouldBeFound("timeZone.specified=true");

        // Get all the npaReportList where timeZone is null
        defaultNpaReportShouldNotBeFound("timeZone.specified=false");
    }

    @Test
    @Transactional
    void getAllNpaReportsByTimeZoneContainsSomething() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where timeZone contains DEFAULT_TIME_ZONE
        defaultNpaReportShouldBeFound("timeZone.contains=" + DEFAULT_TIME_ZONE);

        // Get all the npaReportList where timeZone contains UPDATED_TIME_ZONE
        defaultNpaReportShouldNotBeFound("timeZone.contains=" + UPDATED_TIME_ZONE);
    }

    @Test
    @Transactional
    void getAllNpaReportsByTimeZoneNotContainsSomething() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where timeZone does not contain DEFAULT_TIME_ZONE
        defaultNpaReportShouldNotBeFound("timeZone.doesNotContain=" + DEFAULT_TIME_ZONE);

        // Get all the npaReportList where timeZone does not contain UPDATED_TIME_ZONE
        defaultNpaReportShouldBeFound("timeZone.doesNotContain=" + UPDATED_TIME_ZONE);
    }

    @Test
    @Transactional
    void getAllNpaReportsByAreaServedIsEqualToSomething() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where areaServed equals to DEFAULT_AREA_SERVED
        defaultNpaReportShouldBeFound("areaServed.equals=" + DEFAULT_AREA_SERVED);

        // Get all the npaReportList where areaServed equals to UPDATED_AREA_SERVED
        defaultNpaReportShouldNotBeFound("areaServed.equals=" + UPDATED_AREA_SERVED);
    }

    @Test
    @Transactional
    void getAllNpaReportsByAreaServedIsNotEqualToSomething() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where areaServed not equals to DEFAULT_AREA_SERVED
        defaultNpaReportShouldNotBeFound("areaServed.notEquals=" + DEFAULT_AREA_SERVED);

        // Get all the npaReportList where areaServed not equals to UPDATED_AREA_SERVED
        defaultNpaReportShouldBeFound("areaServed.notEquals=" + UPDATED_AREA_SERVED);
    }

    @Test
    @Transactional
    void getAllNpaReportsByAreaServedIsInShouldWork() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where areaServed in DEFAULT_AREA_SERVED or UPDATED_AREA_SERVED
        defaultNpaReportShouldBeFound("areaServed.in=" + DEFAULT_AREA_SERVED + "," + UPDATED_AREA_SERVED);

        // Get all the npaReportList where areaServed equals to UPDATED_AREA_SERVED
        defaultNpaReportShouldNotBeFound("areaServed.in=" + UPDATED_AREA_SERVED);
    }

    @Test
    @Transactional
    void getAllNpaReportsByAreaServedIsNullOrNotNull() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where areaServed is not null
        defaultNpaReportShouldBeFound("areaServed.specified=true");

        // Get all the npaReportList where areaServed is null
        defaultNpaReportShouldNotBeFound("areaServed.specified=false");
    }

    @Test
    @Transactional
    void getAllNpaReportsByAreaServedContainsSomething() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where areaServed contains DEFAULT_AREA_SERVED
        defaultNpaReportShouldBeFound("areaServed.contains=" + DEFAULT_AREA_SERVED);

        // Get all the npaReportList where areaServed contains UPDATED_AREA_SERVED
        defaultNpaReportShouldNotBeFound("areaServed.contains=" + UPDATED_AREA_SERVED);
    }

    @Test
    @Transactional
    void getAllNpaReportsByAreaServedNotContainsSomething() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where areaServed does not contain DEFAULT_AREA_SERVED
        defaultNpaReportShouldNotBeFound("areaServed.doesNotContain=" + DEFAULT_AREA_SERVED);

        // Get all the npaReportList where areaServed does not contain UPDATED_AREA_SERVED
        defaultNpaReportShouldBeFound("areaServed.doesNotContain=" + UPDATED_AREA_SERVED);
    }

    @Test
    @Transactional
    void getAllNpaReportsByMapIsEqualToSomething() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where map equals to DEFAULT_MAP
        defaultNpaReportShouldBeFound("map.equals=" + DEFAULT_MAP);

        // Get all the npaReportList where map equals to UPDATED_MAP
        defaultNpaReportShouldNotBeFound("map.equals=" + UPDATED_MAP);
    }

    @Test
    @Transactional
    void getAllNpaReportsByMapIsNotEqualToSomething() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where map not equals to DEFAULT_MAP
        defaultNpaReportShouldNotBeFound("map.notEquals=" + DEFAULT_MAP);

        // Get all the npaReportList where map not equals to UPDATED_MAP
        defaultNpaReportShouldBeFound("map.notEquals=" + UPDATED_MAP);
    }

    @Test
    @Transactional
    void getAllNpaReportsByMapIsInShouldWork() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where map in DEFAULT_MAP or UPDATED_MAP
        defaultNpaReportShouldBeFound("map.in=" + DEFAULT_MAP + "," + UPDATED_MAP);

        // Get all the npaReportList where map equals to UPDATED_MAP
        defaultNpaReportShouldNotBeFound("map.in=" + UPDATED_MAP);
    }

    @Test
    @Transactional
    void getAllNpaReportsByMapIsNullOrNotNull() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where map is not null
        defaultNpaReportShouldBeFound("map.specified=true");

        // Get all the npaReportList where map is null
        defaultNpaReportShouldNotBeFound("map.specified=false");
    }

    @Test
    @Transactional
    void getAllNpaReportsByMapContainsSomething() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where map contains DEFAULT_MAP
        defaultNpaReportShouldBeFound("map.contains=" + DEFAULT_MAP);

        // Get all the npaReportList where map contains UPDATED_MAP
        defaultNpaReportShouldNotBeFound("map.contains=" + UPDATED_MAP);
    }

    @Test
    @Transactional
    void getAllNpaReportsByMapNotContainsSomething() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where map does not contain DEFAULT_MAP
        defaultNpaReportShouldNotBeFound("map.doesNotContain=" + DEFAULT_MAP);

        // Get all the npaReportList where map does not contain UPDATED_MAP
        defaultNpaReportShouldBeFound("map.doesNotContain=" + UPDATED_MAP);
    }

    @Test
    @Transactional
    void getAllNpaReportsByInJeopardyIsEqualToSomething() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where inJeopardy equals to DEFAULT_IN_JEOPARDY
        defaultNpaReportShouldBeFound("inJeopardy.equals=" + DEFAULT_IN_JEOPARDY);

        // Get all the npaReportList where inJeopardy equals to UPDATED_IN_JEOPARDY
        defaultNpaReportShouldNotBeFound("inJeopardy.equals=" + UPDATED_IN_JEOPARDY);
    }

    @Test
    @Transactional
    void getAllNpaReportsByInJeopardyIsNotEqualToSomething() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where inJeopardy not equals to DEFAULT_IN_JEOPARDY
        defaultNpaReportShouldNotBeFound("inJeopardy.notEquals=" + DEFAULT_IN_JEOPARDY);

        // Get all the npaReportList where inJeopardy not equals to UPDATED_IN_JEOPARDY
        defaultNpaReportShouldBeFound("inJeopardy.notEquals=" + UPDATED_IN_JEOPARDY);
    }

    @Test
    @Transactional
    void getAllNpaReportsByInJeopardyIsInShouldWork() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where inJeopardy in DEFAULT_IN_JEOPARDY or UPDATED_IN_JEOPARDY
        defaultNpaReportShouldBeFound("inJeopardy.in=" + DEFAULT_IN_JEOPARDY + "," + UPDATED_IN_JEOPARDY);

        // Get all the npaReportList where inJeopardy equals to UPDATED_IN_JEOPARDY
        defaultNpaReportShouldNotBeFound("inJeopardy.in=" + UPDATED_IN_JEOPARDY);
    }

    @Test
    @Transactional
    void getAllNpaReportsByInJeopardyIsNullOrNotNull() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where inJeopardy is not null
        defaultNpaReportShouldBeFound("inJeopardy.specified=true");

        // Get all the npaReportList where inJeopardy is null
        defaultNpaReportShouldNotBeFound("inJeopardy.specified=false");
    }

    @Test
    @Transactional
    void getAllNpaReportsByInJeopardyContainsSomething() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where inJeopardy contains DEFAULT_IN_JEOPARDY
        defaultNpaReportShouldBeFound("inJeopardy.contains=" + DEFAULT_IN_JEOPARDY);

        // Get all the npaReportList where inJeopardy contains UPDATED_IN_JEOPARDY
        defaultNpaReportShouldNotBeFound("inJeopardy.contains=" + UPDATED_IN_JEOPARDY);
    }

    @Test
    @Transactional
    void getAllNpaReportsByInJeopardyNotContainsSomething() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where inJeopardy does not contain DEFAULT_IN_JEOPARDY
        defaultNpaReportShouldNotBeFound("inJeopardy.doesNotContain=" + DEFAULT_IN_JEOPARDY);

        // Get all the npaReportList where inJeopardy does not contain UPDATED_IN_JEOPARDY
        defaultNpaReportShouldBeFound("inJeopardy.doesNotContain=" + UPDATED_IN_JEOPARDY);
    }

    @Test
    @Transactional
    void getAllNpaReportsByReliefPlanningInProgressIsEqualToSomething() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where reliefPlanningInProgress equals to DEFAULT_RELIEF_PLANNING_IN_PROGRESS
        defaultNpaReportShouldBeFound("reliefPlanningInProgress.equals=" + DEFAULT_RELIEF_PLANNING_IN_PROGRESS);

        // Get all the npaReportList where reliefPlanningInProgress equals to UPDATED_RELIEF_PLANNING_IN_PROGRESS
        defaultNpaReportShouldNotBeFound("reliefPlanningInProgress.equals=" + UPDATED_RELIEF_PLANNING_IN_PROGRESS);
    }

    @Test
    @Transactional
    void getAllNpaReportsByReliefPlanningInProgressIsNotEqualToSomething() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where reliefPlanningInProgress not equals to DEFAULT_RELIEF_PLANNING_IN_PROGRESS
        defaultNpaReportShouldNotBeFound("reliefPlanningInProgress.notEquals=" + DEFAULT_RELIEF_PLANNING_IN_PROGRESS);

        // Get all the npaReportList where reliefPlanningInProgress not equals to UPDATED_RELIEF_PLANNING_IN_PROGRESS
        defaultNpaReportShouldBeFound("reliefPlanningInProgress.notEquals=" + UPDATED_RELIEF_PLANNING_IN_PROGRESS);
    }

    @Test
    @Transactional
    void getAllNpaReportsByReliefPlanningInProgressIsInShouldWork() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where reliefPlanningInProgress in DEFAULT_RELIEF_PLANNING_IN_PROGRESS or UPDATED_RELIEF_PLANNING_IN_PROGRESS
        defaultNpaReportShouldBeFound(
            "reliefPlanningInProgress.in=" + DEFAULT_RELIEF_PLANNING_IN_PROGRESS + "," + UPDATED_RELIEF_PLANNING_IN_PROGRESS
        );

        // Get all the npaReportList where reliefPlanningInProgress equals to UPDATED_RELIEF_PLANNING_IN_PROGRESS
        defaultNpaReportShouldNotBeFound("reliefPlanningInProgress.in=" + UPDATED_RELIEF_PLANNING_IN_PROGRESS);
    }

    @Test
    @Transactional
    void getAllNpaReportsByReliefPlanningInProgressIsNullOrNotNull() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where reliefPlanningInProgress is not null
        defaultNpaReportShouldBeFound("reliefPlanningInProgress.specified=true");

        // Get all the npaReportList where reliefPlanningInProgress is null
        defaultNpaReportShouldNotBeFound("reliefPlanningInProgress.specified=false");
    }

    @Test
    @Transactional
    void getAllNpaReportsByReliefPlanningInProgressContainsSomething() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where reliefPlanningInProgress contains DEFAULT_RELIEF_PLANNING_IN_PROGRESS
        defaultNpaReportShouldBeFound("reliefPlanningInProgress.contains=" + DEFAULT_RELIEF_PLANNING_IN_PROGRESS);

        // Get all the npaReportList where reliefPlanningInProgress contains UPDATED_RELIEF_PLANNING_IN_PROGRESS
        defaultNpaReportShouldNotBeFound("reliefPlanningInProgress.contains=" + UPDATED_RELIEF_PLANNING_IN_PROGRESS);
    }

    @Test
    @Transactional
    void getAllNpaReportsByReliefPlanningInProgressNotContainsSomething() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where reliefPlanningInProgress does not contain DEFAULT_RELIEF_PLANNING_IN_PROGRESS
        defaultNpaReportShouldNotBeFound("reliefPlanningInProgress.doesNotContain=" + DEFAULT_RELIEF_PLANNING_IN_PROGRESS);

        // Get all the npaReportList where reliefPlanningInProgress does not contain UPDATED_RELIEF_PLANNING_IN_PROGRESS
        defaultNpaReportShouldBeFound("reliefPlanningInProgress.doesNotContain=" + UPDATED_RELIEF_PLANNING_IN_PROGRESS);
    }

    @Test
    @Transactional
    void getAllNpaReportsByHomeNpaLocalCallsIsEqualToSomething() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where homeNpaLocalCalls equals to DEFAULT_HOME_NPA_LOCAL_CALLS
        defaultNpaReportShouldBeFound("homeNpaLocalCalls.equals=" + DEFAULT_HOME_NPA_LOCAL_CALLS);

        // Get all the npaReportList where homeNpaLocalCalls equals to UPDATED_HOME_NPA_LOCAL_CALLS
        defaultNpaReportShouldNotBeFound("homeNpaLocalCalls.equals=" + UPDATED_HOME_NPA_LOCAL_CALLS);
    }

    @Test
    @Transactional
    void getAllNpaReportsByHomeNpaLocalCallsIsNotEqualToSomething() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where homeNpaLocalCalls not equals to DEFAULT_HOME_NPA_LOCAL_CALLS
        defaultNpaReportShouldNotBeFound("homeNpaLocalCalls.notEquals=" + DEFAULT_HOME_NPA_LOCAL_CALLS);

        // Get all the npaReportList where homeNpaLocalCalls not equals to UPDATED_HOME_NPA_LOCAL_CALLS
        defaultNpaReportShouldBeFound("homeNpaLocalCalls.notEquals=" + UPDATED_HOME_NPA_LOCAL_CALLS);
    }

    @Test
    @Transactional
    void getAllNpaReportsByHomeNpaLocalCallsIsInShouldWork() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where homeNpaLocalCalls in DEFAULT_HOME_NPA_LOCAL_CALLS or UPDATED_HOME_NPA_LOCAL_CALLS
        defaultNpaReportShouldBeFound("homeNpaLocalCalls.in=" + DEFAULT_HOME_NPA_LOCAL_CALLS + "," + UPDATED_HOME_NPA_LOCAL_CALLS);

        // Get all the npaReportList where homeNpaLocalCalls equals to UPDATED_HOME_NPA_LOCAL_CALLS
        defaultNpaReportShouldNotBeFound("homeNpaLocalCalls.in=" + UPDATED_HOME_NPA_LOCAL_CALLS);
    }

    @Test
    @Transactional
    void getAllNpaReportsByHomeNpaLocalCallsIsNullOrNotNull() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where homeNpaLocalCalls is not null
        defaultNpaReportShouldBeFound("homeNpaLocalCalls.specified=true");

        // Get all the npaReportList where homeNpaLocalCalls is null
        defaultNpaReportShouldNotBeFound("homeNpaLocalCalls.specified=false");
    }

    @Test
    @Transactional
    void getAllNpaReportsByHomeNpaLocalCallsContainsSomething() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where homeNpaLocalCalls contains DEFAULT_HOME_NPA_LOCAL_CALLS
        defaultNpaReportShouldBeFound("homeNpaLocalCalls.contains=" + DEFAULT_HOME_NPA_LOCAL_CALLS);

        // Get all the npaReportList where homeNpaLocalCalls contains UPDATED_HOME_NPA_LOCAL_CALLS
        defaultNpaReportShouldNotBeFound("homeNpaLocalCalls.contains=" + UPDATED_HOME_NPA_LOCAL_CALLS);
    }

    @Test
    @Transactional
    void getAllNpaReportsByHomeNpaLocalCallsNotContainsSomething() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where homeNpaLocalCalls does not contain DEFAULT_HOME_NPA_LOCAL_CALLS
        defaultNpaReportShouldNotBeFound("homeNpaLocalCalls.doesNotContain=" + DEFAULT_HOME_NPA_LOCAL_CALLS);

        // Get all the npaReportList where homeNpaLocalCalls does not contain UPDATED_HOME_NPA_LOCAL_CALLS
        defaultNpaReportShouldBeFound("homeNpaLocalCalls.doesNotContain=" + UPDATED_HOME_NPA_LOCAL_CALLS);
    }

    @Test
    @Transactional
    void getAllNpaReportsByHomeNpaTollCallsIsEqualToSomething() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where homeNpaTollCalls equals to DEFAULT_HOME_NPA_TOLL_CALLS
        defaultNpaReportShouldBeFound("homeNpaTollCalls.equals=" + DEFAULT_HOME_NPA_TOLL_CALLS);

        // Get all the npaReportList where homeNpaTollCalls equals to UPDATED_HOME_NPA_TOLL_CALLS
        defaultNpaReportShouldNotBeFound("homeNpaTollCalls.equals=" + UPDATED_HOME_NPA_TOLL_CALLS);
    }

    @Test
    @Transactional
    void getAllNpaReportsByHomeNpaTollCallsIsNotEqualToSomething() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where homeNpaTollCalls not equals to DEFAULT_HOME_NPA_TOLL_CALLS
        defaultNpaReportShouldNotBeFound("homeNpaTollCalls.notEquals=" + DEFAULT_HOME_NPA_TOLL_CALLS);

        // Get all the npaReportList where homeNpaTollCalls not equals to UPDATED_HOME_NPA_TOLL_CALLS
        defaultNpaReportShouldBeFound("homeNpaTollCalls.notEquals=" + UPDATED_HOME_NPA_TOLL_CALLS);
    }

    @Test
    @Transactional
    void getAllNpaReportsByHomeNpaTollCallsIsInShouldWork() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where homeNpaTollCalls in DEFAULT_HOME_NPA_TOLL_CALLS or UPDATED_HOME_NPA_TOLL_CALLS
        defaultNpaReportShouldBeFound("homeNpaTollCalls.in=" + DEFAULT_HOME_NPA_TOLL_CALLS + "," + UPDATED_HOME_NPA_TOLL_CALLS);

        // Get all the npaReportList where homeNpaTollCalls equals to UPDATED_HOME_NPA_TOLL_CALLS
        defaultNpaReportShouldNotBeFound("homeNpaTollCalls.in=" + UPDATED_HOME_NPA_TOLL_CALLS);
    }

    @Test
    @Transactional
    void getAllNpaReportsByHomeNpaTollCallsIsNullOrNotNull() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where homeNpaTollCalls is not null
        defaultNpaReportShouldBeFound("homeNpaTollCalls.specified=true");

        // Get all the npaReportList where homeNpaTollCalls is null
        defaultNpaReportShouldNotBeFound("homeNpaTollCalls.specified=false");
    }

    @Test
    @Transactional
    void getAllNpaReportsByHomeNpaTollCallsContainsSomething() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where homeNpaTollCalls contains DEFAULT_HOME_NPA_TOLL_CALLS
        defaultNpaReportShouldBeFound("homeNpaTollCalls.contains=" + DEFAULT_HOME_NPA_TOLL_CALLS);

        // Get all the npaReportList where homeNpaTollCalls contains UPDATED_HOME_NPA_TOLL_CALLS
        defaultNpaReportShouldNotBeFound("homeNpaTollCalls.contains=" + UPDATED_HOME_NPA_TOLL_CALLS);
    }

    @Test
    @Transactional
    void getAllNpaReportsByHomeNpaTollCallsNotContainsSomething() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where homeNpaTollCalls does not contain DEFAULT_HOME_NPA_TOLL_CALLS
        defaultNpaReportShouldNotBeFound("homeNpaTollCalls.doesNotContain=" + DEFAULT_HOME_NPA_TOLL_CALLS);

        // Get all the npaReportList where homeNpaTollCalls does not contain UPDATED_HOME_NPA_TOLL_CALLS
        defaultNpaReportShouldBeFound("homeNpaTollCalls.doesNotContain=" + UPDATED_HOME_NPA_TOLL_CALLS);
    }

    @Test
    @Transactional
    void getAllNpaReportsByForeignNpaLocalCallsIsEqualToSomething() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where foreignNpaLocalCalls equals to DEFAULT_FOREIGN_NPA_LOCAL_CALLS
        defaultNpaReportShouldBeFound("foreignNpaLocalCalls.equals=" + DEFAULT_FOREIGN_NPA_LOCAL_CALLS);

        // Get all the npaReportList where foreignNpaLocalCalls equals to UPDATED_FOREIGN_NPA_LOCAL_CALLS
        defaultNpaReportShouldNotBeFound("foreignNpaLocalCalls.equals=" + UPDATED_FOREIGN_NPA_LOCAL_CALLS);
    }

    @Test
    @Transactional
    void getAllNpaReportsByForeignNpaLocalCallsIsNotEqualToSomething() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where foreignNpaLocalCalls not equals to DEFAULT_FOREIGN_NPA_LOCAL_CALLS
        defaultNpaReportShouldNotBeFound("foreignNpaLocalCalls.notEquals=" + DEFAULT_FOREIGN_NPA_LOCAL_CALLS);

        // Get all the npaReportList where foreignNpaLocalCalls not equals to UPDATED_FOREIGN_NPA_LOCAL_CALLS
        defaultNpaReportShouldBeFound("foreignNpaLocalCalls.notEquals=" + UPDATED_FOREIGN_NPA_LOCAL_CALLS);
    }

    @Test
    @Transactional
    void getAllNpaReportsByForeignNpaLocalCallsIsInShouldWork() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where foreignNpaLocalCalls in DEFAULT_FOREIGN_NPA_LOCAL_CALLS or UPDATED_FOREIGN_NPA_LOCAL_CALLS
        defaultNpaReportShouldBeFound("foreignNpaLocalCalls.in=" + DEFAULT_FOREIGN_NPA_LOCAL_CALLS + "," + UPDATED_FOREIGN_NPA_LOCAL_CALLS);

        // Get all the npaReportList where foreignNpaLocalCalls equals to UPDATED_FOREIGN_NPA_LOCAL_CALLS
        defaultNpaReportShouldNotBeFound("foreignNpaLocalCalls.in=" + UPDATED_FOREIGN_NPA_LOCAL_CALLS);
    }

    @Test
    @Transactional
    void getAllNpaReportsByForeignNpaLocalCallsIsNullOrNotNull() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where foreignNpaLocalCalls is not null
        defaultNpaReportShouldBeFound("foreignNpaLocalCalls.specified=true");

        // Get all the npaReportList where foreignNpaLocalCalls is null
        defaultNpaReportShouldNotBeFound("foreignNpaLocalCalls.specified=false");
    }

    @Test
    @Transactional
    void getAllNpaReportsByForeignNpaLocalCallsContainsSomething() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where foreignNpaLocalCalls contains DEFAULT_FOREIGN_NPA_LOCAL_CALLS
        defaultNpaReportShouldBeFound("foreignNpaLocalCalls.contains=" + DEFAULT_FOREIGN_NPA_LOCAL_CALLS);

        // Get all the npaReportList where foreignNpaLocalCalls contains UPDATED_FOREIGN_NPA_LOCAL_CALLS
        defaultNpaReportShouldNotBeFound("foreignNpaLocalCalls.contains=" + UPDATED_FOREIGN_NPA_LOCAL_CALLS);
    }

    @Test
    @Transactional
    void getAllNpaReportsByForeignNpaLocalCallsNotContainsSomething() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where foreignNpaLocalCalls does not contain DEFAULT_FOREIGN_NPA_LOCAL_CALLS
        defaultNpaReportShouldNotBeFound("foreignNpaLocalCalls.doesNotContain=" + DEFAULT_FOREIGN_NPA_LOCAL_CALLS);

        // Get all the npaReportList where foreignNpaLocalCalls does not contain UPDATED_FOREIGN_NPA_LOCAL_CALLS
        defaultNpaReportShouldBeFound("foreignNpaLocalCalls.doesNotContain=" + UPDATED_FOREIGN_NPA_LOCAL_CALLS);
    }

    @Test
    @Transactional
    void getAllNpaReportsByForeignNpaTollCallsIsEqualToSomething() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where foreignNpaTollCalls equals to DEFAULT_FOREIGN_NPA_TOLL_CALLS
        defaultNpaReportShouldBeFound("foreignNpaTollCalls.equals=" + DEFAULT_FOREIGN_NPA_TOLL_CALLS);

        // Get all the npaReportList where foreignNpaTollCalls equals to UPDATED_FOREIGN_NPA_TOLL_CALLS
        defaultNpaReportShouldNotBeFound("foreignNpaTollCalls.equals=" + UPDATED_FOREIGN_NPA_TOLL_CALLS);
    }

    @Test
    @Transactional
    void getAllNpaReportsByForeignNpaTollCallsIsNotEqualToSomething() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where foreignNpaTollCalls not equals to DEFAULT_FOREIGN_NPA_TOLL_CALLS
        defaultNpaReportShouldNotBeFound("foreignNpaTollCalls.notEquals=" + DEFAULT_FOREIGN_NPA_TOLL_CALLS);

        // Get all the npaReportList where foreignNpaTollCalls not equals to UPDATED_FOREIGN_NPA_TOLL_CALLS
        defaultNpaReportShouldBeFound("foreignNpaTollCalls.notEquals=" + UPDATED_FOREIGN_NPA_TOLL_CALLS);
    }

    @Test
    @Transactional
    void getAllNpaReportsByForeignNpaTollCallsIsInShouldWork() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where foreignNpaTollCalls in DEFAULT_FOREIGN_NPA_TOLL_CALLS or UPDATED_FOREIGN_NPA_TOLL_CALLS
        defaultNpaReportShouldBeFound("foreignNpaTollCalls.in=" + DEFAULT_FOREIGN_NPA_TOLL_CALLS + "," + UPDATED_FOREIGN_NPA_TOLL_CALLS);

        // Get all the npaReportList where foreignNpaTollCalls equals to UPDATED_FOREIGN_NPA_TOLL_CALLS
        defaultNpaReportShouldNotBeFound("foreignNpaTollCalls.in=" + UPDATED_FOREIGN_NPA_TOLL_CALLS);
    }

    @Test
    @Transactional
    void getAllNpaReportsByForeignNpaTollCallsIsNullOrNotNull() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where foreignNpaTollCalls is not null
        defaultNpaReportShouldBeFound("foreignNpaTollCalls.specified=true");

        // Get all the npaReportList where foreignNpaTollCalls is null
        defaultNpaReportShouldNotBeFound("foreignNpaTollCalls.specified=false");
    }

    @Test
    @Transactional
    void getAllNpaReportsByForeignNpaTollCallsContainsSomething() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where foreignNpaTollCalls contains DEFAULT_FOREIGN_NPA_TOLL_CALLS
        defaultNpaReportShouldBeFound("foreignNpaTollCalls.contains=" + DEFAULT_FOREIGN_NPA_TOLL_CALLS);

        // Get all the npaReportList where foreignNpaTollCalls contains UPDATED_FOREIGN_NPA_TOLL_CALLS
        defaultNpaReportShouldNotBeFound("foreignNpaTollCalls.contains=" + UPDATED_FOREIGN_NPA_TOLL_CALLS);
    }

    @Test
    @Transactional
    void getAllNpaReportsByForeignNpaTollCallsNotContainsSomething() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where foreignNpaTollCalls does not contain DEFAULT_FOREIGN_NPA_TOLL_CALLS
        defaultNpaReportShouldNotBeFound("foreignNpaTollCalls.doesNotContain=" + DEFAULT_FOREIGN_NPA_TOLL_CALLS);

        // Get all the npaReportList where foreignNpaTollCalls does not contain UPDATED_FOREIGN_NPA_TOLL_CALLS
        defaultNpaReportShouldBeFound("foreignNpaTollCalls.doesNotContain=" + UPDATED_FOREIGN_NPA_TOLL_CALLS);
    }

    @Test
    @Transactional
    void getAllNpaReportsByPermHnpaLocalCallsIsEqualToSomething() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where permHnpaLocalCalls equals to DEFAULT_PERM_HNPA_LOCAL_CALLS
        defaultNpaReportShouldBeFound("permHnpaLocalCalls.equals=" + DEFAULT_PERM_HNPA_LOCAL_CALLS);

        // Get all the npaReportList where permHnpaLocalCalls equals to UPDATED_PERM_HNPA_LOCAL_CALLS
        defaultNpaReportShouldNotBeFound("permHnpaLocalCalls.equals=" + UPDATED_PERM_HNPA_LOCAL_CALLS);
    }

    @Test
    @Transactional
    void getAllNpaReportsByPermHnpaLocalCallsIsNotEqualToSomething() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where permHnpaLocalCalls not equals to DEFAULT_PERM_HNPA_LOCAL_CALLS
        defaultNpaReportShouldNotBeFound("permHnpaLocalCalls.notEquals=" + DEFAULT_PERM_HNPA_LOCAL_CALLS);

        // Get all the npaReportList where permHnpaLocalCalls not equals to UPDATED_PERM_HNPA_LOCAL_CALLS
        defaultNpaReportShouldBeFound("permHnpaLocalCalls.notEquals=" + UPDATED_PERM_HNPA_LOCAL_CALLS);
    }

    @Test
    @Transactional
    void getAllNpaReportsByPermHnpaLocalCallsIsInShouldWork() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where permHnpaLocalCalls in DEFAULT_PERM_HNPA_LOCAL_CALLS or UPDATED_PERM_HNPA_LOCAL_CALLS
        defaultNpaReportShouldBeFound("permHnpaLocalCalls.in=" + DEFAULT_PERM_HNPA_LOCAL_CALLS + "," + UPDATED_PERM_HNPA_LOCAL_CALLS);

        // Get all the npaReportList where permHnpaLocalCalls equals to UPDATED_PERM_HNPA_LOCAL_CALLS
        defaultNpaReportShouldNotBeFound("permHnpaLocalCalls.in=" + UPDATED_PERM_HNPA_LOCAL_CALLS);
    }

    @Test
    @Transactional
    void getAllNpaReportsByPermHnpaLocalCallsIsNullOrNotNull() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where permHnpaLocalCalls is not null
        defaultNpaReportShouldBeFound("permHnpaLocalCalls.specified=true");

        // Get all the npaReportList where permHnpaLocalCalls is null
        defaultNpaReportShouldNotBeFound("permHnpaLocalCalls.specified=false");
    }

    @Test
    @Transactional
    void getAllNpaReportsByPermHnpaLocalCallsContainsSomething() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where permHnpaLocalCalls contains DEFAULT_PERM_HNPA_LOCAL_CALLS
        defaultNpaReportShouldBeFound("permHnpaLocalCalls.contains=" + DEFAULT_PERM_HNPA_LOCAL_CALLS);

        // Get all the npaReportList where permHnpaLocalCalls contains UPDATED_PERM_HNPA_LOCAL_CALLS
        defaultNpaReportShouldNotBeFound("permHnpaLocalCalls.contains=" + UPDATED_PERM_HNPA_LOCAL_CALLS);
    }

    @Test
    @Transactional
    void getAllNpaReportsByPermHnpaLocalCallsNotContainsSomething() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where permHnpaLocalCalls does not contain DEFAULT_PERM_HNPA_LOCAL_CALLS
        defaultNpaReportShouldNotBeFound("permHnpaLocalCalls.doesNotContain=" + DEFAULT_PERM_HNPA_LOCAL_CALLS);

        // Get all the npaReportList where permHnpaLocalCalls does not contain UPDATED_PERM_HNPA_LOCAL_CALLS
        defaultNpaReportShouldBeFound("permHnpaLocalCalls.doesNotContain=" + UPDATED_PERM_HNPA_LOCAL_CALLS);
    }

    @Test
    @Transactional
    void getAllNpaReportsByPermHnpaTollCallsIsEqualToSomething() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where permHnpaTollCalls equals to DEFAULT_PERM_HNPA_TOLL_CALLS
        defaultNpaReportShouldBeFound("permHnpaTollCalls.equals=" + DEFAULT_PERM_HNPA_TOLL_CALLS);

        // Get all the npaReportList where permHnpaTollCalls equals to UPDATED_PERM_HNPA_TOLL_CALLS
        defaultNpaReportShouldNotBeFound("permHnpaTollCalls.equals=" + UPDATED_PERM_HNPA_TOLL_CALLS);
    }

    @Test
    @Transactional
    void getAllNpaReportsByPermHnpaTollCallsIsNotEqualToSomething() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where permHnpaTollCalls not equals to DEFAULT_PERM_HNPA_TOLL_CALLS
        defaultNpaReportShouldNotBeFound("permHnpaTollCalls.notEquals=" + DEFAULT_PERM_HNPA_TOLL_CALLS);

        // Get all the npaReportList where permHnpaTollCalls not equals to UPDATED_PERM_HNPA_TOLL_CALLS
        defaultNpaReportShouldBeFound("permHnpaTollCalls.notEquals=" + UPDATED_PERM_HNPA_TOLL_CALLS);
    }

    @Test
    @Transactional
    void getAllNpaReportsByPermHnpaTollCallsIsInShouldWork() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where permHnpaTollCalls in DEFAULT_PERM_HNPA_TOLL_CALLS or UPDATED_PERM_HNPA_TOLL_CALLS
        defaultNpaReportShouldBeFound("permHnpaTollCalls.in=" + DEFAULT_PERM_HNPA_TOLL_CALLS + "," + UPDATED_PERM_HNPA_TOLL_CALLS);

        // Get all the npaReportList where permHnpaTollCalls equals to UPDATED_PERM_HNPA_TOLL_CALLS
        defaultNpaReportShouldNotBeFound("permHnpaTollCalls.in=" + UPDATED_PERM_HNPA_TOLL_CALLS);
    }

    @Test
    @Transactional
    void getAllNpaReportsByPermHnpaTollCallsIsNullOrNotNull() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where permHnpaTollCalls is not null
        defaultNpaReportShouldBeFound("permHnpaTollCalls.specified=true");

        // Get all the npaReportList where permHnpaTollCalls is null
        defaultNpaReportShouldNotBeFound("permHnpaTollCalls.specified=false");
    }

    @Test
    @Transactional
    void getAllNpaReportsByPermHnpaTollCallsContainsSomething() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where permHnpaTollCalls contains DEFAULT_PERM_HNPA_TOLL_CALLS
        defaultNpaReportShouldBeFound("permHnpaTollCalls.contains=" + DEFAULT_PERM_HNPA_TOLL_CALLS);

        // Get all the npaReportList where permHnpaTollCalls contains UPDATED_PERM_HNPA_TOLL_CALLS
        defaultNpaReportShouldNotBeFound("permHnpaTollCalls.contains=" + UPDATED_PERM_HNPA_TOLL_CALLS);
    }

    @Test
    @Transactional
    void getAllNpaReportsByPermHnpaTollCallsNotContainsSomething() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where permHnpaTollCalls does not contain DEFAULT_PERM_HNPA_TOLL_CALLS
        defaultNpaReportShouldNotBeFound("permHnpaTollCalls.doesNotContain=" + DEFAULT_PERM_HNPA_TOLL_CALLS);

        // Get all the npaReportList where permHnpaTollCalls does not contain UPDATED_PERM_HNPA_TOLL_CALLS
        defaultNpaReportShouldBeFound("permHnpaTollCalls.doesNotContain=" + UPDATED_PERM_HNPA_TOLL_CALLS);
    }

    @Test
    @Transactional
    void getAllNpaReportsByPermHnpaForeignLocalCallsIsEqualToSomething() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where permHnpaForeignLocalCalls equals to DEFAULT_PERM_HNPA_FOREIGN_LOCAL_CALLS
        defaultNpaReportShouldBeFound("permHnpaForeignLocalCalls.equals=" + DEFAULT_PERM_HNPA_FOREIGN_LOCAL_CALLS);

        // Get all the npaReportList where permHnpaForeignLocalCalls equals to UPDATED_PERM_HNPA_FOREIGN_LOCAL_CALLS
        defaultNpaReportShouldNotBeFound("permHnpaForeignLocalCalls.equals=" + UPDATED_PERM_HNPA_FOREIGN_LOCAL_CALLS);
    }

    @Test
    @Transactional
    void getAllNpaReportsByPermHnpaForeignLocalCallsIsNotEqualToSomething() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where permHnpaForeignLocalCalls not equals to DEFAULT_PERM_HNPA_FOREIGN_LOCAL_CALLS
        defaultNpaReportShouldNotBeFound("permHnpaForeignLocalCalls.notEquals=" + DEFAULT_PERM_HNPA_FOREIGN_LOCAL_CALLS);

        // Get all the npaReportList where permHnpaForeignLocalCalls not equals to UPDATED_PERM_HNPA_FOREIGN_LOCAL_CALLS
        defaultNpaReportShouldBeFound("permHnpaForeignLocalCalls.notEquals=" + UPDATED_PERM_HNPA_FOREIGN_LOCAL_CALLS);
    }

    @Test
    @Transactional
    void getAllNpaReportsByPermHnpaForeignLocalCallsIsInShouldWork() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where permHnpaForeignLocalCalls in DEFAULT_PERM_HNPA_FOREIGN_LOCAL_CALLS or UPDATED_PERM_HNPA_FOREIGN_LOCAL_CALLS
        defaultNpaReportShouldBeFound(
            "permHnpaForeignLocalCalls.in=" + DEFAULT_PERM_HNPA_FOREIGN_LOCAL_CALLS + "," + UPDATED_PERM_HNPA_FOREIGN_LOCAL_CALLS
        );

        // Get all the npaReportList where permHnpaForeignLocalCalls equals to UPDATED_PERM_HNPA_FOREIGN_LOCAL_CALLS
        defaultNpaReportShouldNotBeFound("permHnpaForeignLocalCalls.in=" + UPDATED_PERM_HNPA_FOREIGN_LOCAL_CALLS);
    }

    @Test
    @Transactional
    void getAllNpaReportsByPermHnpaForeignLocalCallsIsNullOrNotNull() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where permHnpaForeignLocalCalls is not null
        defaultNpaReportShouldBeFound("permHnpaForeignLocalCalls.specified=true");

        // Get all the npaReportList where permHnpaForeignLocalCalls is null
        defaultNpaReportShouldNotBeFound("permHnpaForeignLocalCalls.specified=false");
    }

    @Test
    @Transactional
    void getAllNpaReportsByPermHnpaForeignLocalCallsContainsSomething() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where permHnpaForeignLocalCalls contains DEFAULT_PERM_HNPA_FOREIGN_LOCAL_CALLS
        defaultNpaReportShouldBeFound("permHnpaForeignLocalCalls.contains=" + DEFAULT_PERM_HNPA_FOREIGN_LOCAL_CALLS);

        // Get all the npaReportList where permHnpaForeignLocalCalls contains UPDATED_PERM_HNPA_FOREIGN_LOCAL_CALLS
        defaultNpaReportShouldNotBeFound("permHnpaForeignLocalCalls.contains=" + UPDATED_PERM_HNPA_FOREIGN_LOCAL_CALLS);
    }

    @Test
    @Transactional
    void getAllNpaReportsByPermHnpaForeignLocalCallsNotContainsSomething() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where permHnpaForeignLocalCalls does not contain DEFAULT_PERM_HNPA_FOREIGN_LOCAL_CALLS
        defaultNpaReportShouldNotBeFound("permHnpaForeignLocalCalls.doesNotContain=" + DEFAULT_PERM_HNPA_FOREIGN_LOCAL_CALLS);

        // Get all the npaReportList where permHnpaForeignLocalCalls does not contain UPDATED_PERM_HNPA_FOREIGN_LOCAL_CALLS
        defaultNpaReportShouldBeFound("permHnpaForeignLocalCalls.doesNotContain=" + UPDATED_PERM_HNPA_FOREIGN_LOCAL_CALLS);
    }

    @Test
    @Transactional
    void getAllNpaReportsByDialingPlanNotesIsEqualToSomething() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where dialingPlanNotes equals to DEFAULT_DIALING_PLAN_NOTES
        defaultNpaReportShouldBeFound("dialingPlanNotes.equals=" + DEFAULT_DIALING_PLAN_NOTES);

        // Get all the npaReportList where dialingPlanNotes equals to UPDATED_DIALING_PLAN_NOTES
        defaultNpaReportShouldNotBeFound("dialingPlanNotes.equals=" + UPDATED_DIALING_PLAN_NOTES);
    }

    @Test
    @Transactional
    void getAllNpaReportsByDialingPlanNotesIsNotEqualToSomething() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where dialingPlanNotes not equals to DEFAULT_DIALING_PLAN_NOTES
        defaultNpaReportShouldNotBeFound("dialingPlanNotes.notEquals=" + DEFAULT_DIALING_PLAN_NOTES);

        // Get all the npaReportList where dialingPlanNotes not equals to UPDATED_DIALING_PLAN_NOTES
        defaultNpaReportShouldBeFound("dialingPlanNotes.notEquals=" + UPDATED_DIALING_PLAN_NOTES);
    }

    @Test
    @Transactional
    void getAllNpaReportsByDialingPlanNotesIsInShouldWork() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where dialingPlanNotes in DEFAULT_DIALING_PLAN_NOTES or UPDATED_DIALING_PLAN_NOTES
        defaultNpaReportShouldBeFound("dialingPlanNotes.in=" + DEFAULT_DIALING_PLAN_NOTES + "," + UPDATED_DIALING_PLAN_NOTES);

        // Get all the npaReportList where dialingPlanNotes equals to UPDATED_DIALING_PLAN_NOTES
        defaultNpaReportShouldNotBeFound("dialingPlanNotes.in=" + UPDATED_DIALING_PLAN_NOTES);
    }

    @Test
    @Transactional
    void getAllNpaReportsByDialingPlanNotesIsNullOrNotNull() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where dialingPlanNotes is not null
        defaultNpaReportShouldBeFound("dialingPlanNotes.specified=true");

        // Get all the npaReportList where dialingPlanNotes is null
        defaultNpaReportShouldNotBeFound("dialingPlanNotes.specified=false");
    }

    @Test
    @Transactional
    void getAllNpaReportsByDialingPlanNotesContainsSomething() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where dialingPlanNotes contains DEFAULT_DIALING_PLAN_NOTES
        defaultNpaReportShouldBeFound("dialingPlanNotes.contains=" + DEFAULT_DIALING_PLAN_NOTES);

        // Get all the npaReportList where dialingPlanNotes contains UPDATED_DIALING_PLAN_NOTES
        defaultNpaReportShouldNotBeFound("dialingPlanNotes.contains=" + UPDATED_DIALING_PLAN_NOTES);
    }

    @Test
    @Transactional
    void getAllNpaReportsByDialingPlanNotesNotContainsSomething() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        // Get all the npaReportList where dialingPlanNotes does not contain DEFAULT_DIALING_PLAN_NOTES
        defaultNpaReportShouldNotBeFound("dialingPlanNotes.doesNotContain=" + DEFAULT_DIALING_PLAN_NOTES);

        // Get all the npaReportList where dialingPlanNotes does not contain UPDATED_DIALING_PLAN_NOTES
        defaultNpaReportShouldBeFound("dialingPlanNotes.doesNotContain=" + UPDATED_DIALING_PLAN_NOTES);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultNpaReportShouldBeFound(String filter) throws Exception {
        restNpaReportMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(npaReport.getId().intValue())))
            .andExpect(jsonPath("$.[*].npaId").value(hasItem(DEFAULT_NPA_ID.intValue())))
            .andExpect(jsonPath("$.[*].typeOfCode").value(hasItem(DEFAULT_TYPE_OF_CODE)))
            .andExpect(jsonPath("$.[*].assignable").value(hasItem(DEFAULT_ASSIGNABLE)))
            .andExpect(jsonPath("$.[*].explanation").value(hasItem(DEFAULT_EXPLANATION)))
            .andExpect(jsonPath("$.[*].reserved").value(hasItem(DEFAULT_RESERVED)))
            .andExpect(jsonPath("$.[*].assigned").value(hasItem(DEFAULT_ASSIGNED)))
            .andExpect(jsonPath("$.[*].assignmentDate").value(hasItem(DEFAULT_ASSIGNMENT_DATE.toString())))
            .andExpect(jsonPath("$.[*].useType").value(hasItem(DEFAULT_USE_TYPE)))
            .andExpect(jsonPath("$.[*].location").value(hasItem(DEFAULT_LOCATION)))
            .andExpect(jsonPath("$.[*].country").value(hasItem(DEFAULT_COUNTRY)))
            .andExpect(jsonPath("$.[*].inService").value(hasItem(DEFAULT_IN_SERVICE)))
            .andExpect(jsonPath("$.[*].inServiceDate").value(hasItem(DEFAULT_IN_SERVICE_DATE.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].planningLetters").value(hasItem(DEFAULT_PLANNING_LETTERS)))
            .andExpect(jsonPath("$.[*].notes").value(hasItem(DEFAULT_NOTES)))
            .andExpect(jsonPath("$.[*].overlay").value(hasItem(DEFAULT_OVERLAY)))
            .andExpect(jsonPath("$.[*].overlayComplex").value(hasItem(DEFAULT_OVERLAY_COMPLEX)))
            .andExpect(jsonPath("$.[*].parentNpaId").value(hasItem(DEFAULT_PARENT_NPA_ID.intValue())))
            .andExpect(jsonPath("$.[*].service").value(hasItem(DEFAULT_SERVICE)))
            .andExpect(jsonPath("$.[*].timeZone").value(hasItem(DEFAULT_TIME_ZONE)))
            .andExpect(jsonPath("$.[*].areaServed").value(hasItem(DEFAULT_AREA_SERVED)))
            .andExpect(jsonPath("$.[*].map").value(hasItem(DEFAULT_MAP)))
            .andExpect(jsonPath("$.[*].inJeopardy").value(hasItem(DEFAULT_IN_JEOPARDY)))
            .andExpect(jsonPath("$.[*].reliefPlanningInProgress").value(hasItem(DEFAULT_RELIEF_PLANNING_IN_PROGRESS)))
            .andExpect(jsonPath("$.[*].homeNpaLocalCalls").value(hasItem(DEFAULT_HOME_NPA_LOCAL_CALLS)))
            .andExpect(jsonPath("$.[*].homeNpaTollCalls").value(hasItem(DEFAULT_HOME_NPA_TOLL_CALLS)))
            .andExpect(jsonPath("$.[*].foreignNpaLocalCalls").value(hasItem(DEFAULT_FOREIGN_NPA_LOCAL_CALLS)))
            .andExpect(jsonPath("$.[*].foreignNpaTollCalls").value(hasItem(DEFAULT_FOREIGN_NPA_TOLL_CALLS)))
            .andExpect(jsonPath("$.[*].permHnpaLocalCalls").value(hasItem(DEFAULT_PERM_HNPA_LOCAL_CALLS)))
            .andExpect(jsonPath("$.[*].permHnpaTollCalls").value(hasItem(DEFAULT_PERM_HNPA_TOLL_CALLS)))
            .andExpect(jsonPath("$.[*].permHnpaForeignLocalCalls").value(hasItem(DEFAULT_PERM_HNPA_FOREIGN_LOCAL_CALLS)))
            .andExpect(jsonPath("$.[*].dialingPlanNotes").value(hasItem(DEFAULT_DIALING_PLAN_NOTES)));

        // Check, that the count call also returns 1
        restNpaReportMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultNpaReportShouldNotBeFound(String filter) throws Exception {
        restNpaReportMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restNpaReportMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    void getNonExistingNpaReport() throws Exception {
        // Get the npaReport
        restNpaReportMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewNpaReport() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        int databaseSizeBeforeUpdate = npaReportRepository.findAll().size();

        // Update the npaReport
        NpaReport updatedNpaReport = npaReportRepository.findById(npaReport.getId()).get();
        // Disconnect from session so that the updates on updatedNpaReport are not directly saved in db
        em.detach(updatedNpaReport);
        updatedNpaReport
            .npaId(UPDATED_NPA_ID)
            .typeOfCode(UPDATED_TYPE_OF_CODE)
            .assignable(UPDATED_ASSIGNABLE)
            .explanation(UPDATED_EXPLANATION)
            .reserved(UPDATED_RESERVED)
            .assigned(UPDATED_ASSIGNED)
            .assignmentDate(UPDATED_ASSIGNMENT_DATE)
            .useType(UPDATED_USE_TYPE)
            .location(UPDATED_LOCATION)
            .country(UPDATED_COUNTRY)
            .inService(UPDATED_IN_SERVICE)
            .inServiceDate(UPDATED_IN_SERVICE_DATE)
            .status(UPDATED_STATUS)
            .planningLetters(UPDATED_PLANNING_LETTERS)
            .notes(UPDATED_NOTES)
            .overlay(UPDATED_OVERLAY)
            .overlayComplex(UPDATED_OVERLAY_COMPLEX)
            .parentNpaId(UPDATED_PARENT_NPA_ID)
            .service(UPDATED_SERVICE)
            .timeZone(UPDATED_TIME_ZONE)
            .areaServed(UPDATED_AREA_SERVED)
            .map(UPDATED_MAP)
            .inJeopardy(UPDATED_IN_JEOPARDY)
            .reliefPlanningInProgress(UPDATED_RELIEF_PLANNING_IN_PROGRESS)
            .homeNpaLocalCalls(UPDATED_HOME_NPA_LOCAL_CALLS)
            .homeNpaTollCalls(UPDATED_HOME_NPA_TOLL_CALLS)
            .foreignNpaLocalCalls(UPDATED_FOREIGN_NPA_LOCAL_CALLS)
            .foreignNpaTollCalls(UPDATED_FOREIGN_NPA_TOLL_CALLS)
            .permHnpaLocalCalls(UPDATED_PERM_HNPA_LOCAL_CALLS)
            .permHnpaTollCalls(UPDATED_PERM_HNPA_TOLL_CALLS)
            .permHnpaForeignLocalCalls(UPDATED_PERM_HNPA_FOREIGN_LOCAL_CALLS)
            .dialingPlanNotes(UPDATED_DIALING_PLAN_NOTES);

        restNpaReportMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedNpaReport.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedNpaReport))
            )
            .andExpect(status().isOk());

        // Validate the NpaReport in the database
        List<NpaReport> npaReportList = npaReportRepository.findAll();
        assertThat(npaReportList).hasSize(databaseSizeBeforeUpdate);
        NpaReport testNpaReport = npaReportList.get(npaReportList.size() - 1);
        assertThat(testNpaReport.getNpaId()).isEqualTo(UPDATED_NPA_ID);
        assertThat(testNpaReport.getTypeOfCode()).isEqualTo(UPDATED_TYPE_OF_CODE);
        assertThat(testNpaReport.getAssignable()).isEqualTo(UPDATED_ASSIGNABLE);
        assertThat(testNpaReport.getExplanation()).isEqualTo(UPDATED_EXPLANATION);
        assertThat(testNpaReport.getReserved()).isEqualTo(UPDATED_RESERVED);
        assertThat(testNpaReport.getAssigned()).isEqualTo(UPDATED_ASSIGNED);
        assertThat(testNpaReport.getAssignmentDate()).isEqualTo(UPDATED_ASSIGNMENT_DATE);
        assertThat(testNpaReport.getUseType()).isEqualTo(UPDATED_USE_TYPE);
        assertThat(testNpaReport.getLocation()).isEqualTo(UPDATED_LOCATION);
        assertThat(testNpaReport.getCountry()).isEqualTo(UPDATED_COUNTRY);
        assertThat(testNpaReport.getInService()).isEqualTo(UPDATED_IN_SERVICE);
        assertThat(testNpaReport.getInServiceDate()).isEqualTo(UPDATED_IN_SERVICE_DATE);
        assertThat(testNpaReport.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testNpaReport.getPlanningLetters()).isEqualTo(UPDATED_PLANNING_LETTERS);
        assertThat(testNpaReport.getNotes()).isEqualTo(UPDATED_NOTES);
        assertThat(testNpaReport.getOverlay()).isEqualTo(UPDATED_OVERLAY);
        assertThat(testNpaReport.getOverlayComplex()).isEqualTo(UPDATED_OVERLAY_COMPLEX);
        assertThat(testNpaReport.getParentNpaId()).isEqualTo(UPDATED_PARENT_NPA_ID);
        assertThat(testNpaReport.getService()).isEqualTo(UPDATED_SERVICE);
        assertThat(testNpaReport.getTimeZone()).isEqualTo(UPDATED_TIME_ZONE);
        assertThat(testNpaReport.getAreaServed()).isEqualTo(UPDATED_AREA_SERVED);
        assertThat(testNpaReport.getMap()).isEqualTo(UPDATED_MAP);
        assertThat(testNpaReport.getInJeopardy()).isEqualTo(UPDATED_IN_JEOPARDY);
        assertThat(testNpaReport.getReliefPlanningInProgress()).isEqualTo(UPDATED_RELIEF_PLANNING_IN_PROGRESS);
        assertThat(testNpaReport.getHomeNpaLocalCalls()).isEqualTo(UPDATED_HOME_NPA_LOCAL_CALLS);
        assertThat(testNpaReport.getHomeNpaTollCalls()).isEqualTo(UPDATED_HOME_NPA_TOLL_CALLS);
        assertThat(testNpaReport.getForeignNpaLocalCalls()).isEqualTo(UPDATED_FOREIGN_NPA_LOCAL_CALLS);
        assertThat(testNpaReport.getForeignNpaTollCalls()).isEqualTo(UPDATED_FOREIGN_NPA_TOLL_CALLS);
        assertThat(testNpaReport.getPermHnpaLocalCalls()).isEqualTo(UPDATED_PERM_HNPA_LOCAL_CALLS);
        assertThat(testNpaReport.getPermHnpaTollCalls()).isEqualTo(UPDATED_PERM_HNPA_TOLL_CALLS);
        assertThat(testNpaReport.getPermHnpaForeignLocalCalls()).isEqualTo(UPDATED_PERM_HNPA_FOREIGN_LOCAL_CALLS);
        assertThat(testNpaReport.getDialingPlanNotes()).isEqualTo(UPDATED_DIALING_PLAN_NOTES);
    }

    @Test
    @Transactional
    void putNonExistingNpaReport() throws Exception {
        int databaseSizeBeforeUpdate = npaReportRepository.findAll().size();
        npaReport.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restNpaReportMockMvc
            .perform(
                put(ENTITY_API_URL_ID, npaReport.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(npaReport))
            )
            .andExpect(status().isBadRequest());

        // Validate the NpaReport in the database
        List<NpaReport> npaReportList = npaReportRepository.findAll();
        assertThat(npaReportList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchNpaReport() throws Exception {
        int databaseSizeBeforeUpdate = npaReportRepository.findAll().size();
        npaReport.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restNpaReportMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(npaReport))
            )
            .andExpect(status().isBadRequest());

        // Validate the NpaReport in the database
        List<NpaReport> npaReportList = npaReportRepository.findAll();
        assertThat(npaReportList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamNpaReport() throws Exception {
        int databaseSizeBeforeUpdate = npaReportRepository.findAll().size();
        npaReport.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restNpaReportMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(npaReport)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the NpaReport in the database
        List<NpaReport> npaReportList = npaReportRepository.findAll();
        assertThat(npaReportList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateNpaReportWithPatch() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        int databaseSizeBeforeUpdate = npaReportRepository.findAll().size();

        // Update the npaReport using partial update
        NpaReport partialUpdatedNpaReport = new NpaReport();
        partialUpdatedNpaReport.setId(npaReport.getId());

        partialUpdatedNpaReport
            .typeOfCode(UPDATED_TYPE_OF_CODE)
            .explanation(UPDATED_EXPLANATION)
            .location(UPDATED_LOCATION)
            .inService(UPDATED_IN_SERVICE)
            .inServiceDate(UPDATED_IN_SERVICE_DATE)
            .notes(UPDATED_NOTES)
            .overlay(UPDATED_OVERLAY)
            .overlayComplex(UPDATED_OVERLAY_COMPLEX)
            .parentNpaId(UPDATED_PARENT_NPA_ID)
            .areaServed(UPDATED_AREA_SERVED)
            .inJeopardy(UPDATED_IN_JEOPARDY)
            .reliefPlanningInProgress(UPDATED_RELIEF_PLANNING_IN_PROGRESS)
            .homeNpaLocalCalls(UPDATED_HOME_NPA_LOCAL_CALLS)
            .permHnpaForeignLocalCalls(UPDATED_PERM_HNPA_FOREIGN_LOCAL_CALLS)
            .dialingPlanNotes(UPDATED_DIALING_PLAN_NOTES);

        restNpaReportMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedNpaReport.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedNpaReport))
            )
            .andExpect(status().isOk());

        // Validate the NpaReport in the database
        List<NpaReport> npaReportList = npaReportRepository.findAll();
        assertThat(npaReportList).hasSize(databaseSizeBeforeUpdate);
        NpaReport testNpaReport = npaReportList.get(npaReportList.size() - 1);
        assertThat(testNpaReport.getNpaId()).isEqualTo(DEFAULT_NPA_ID);
        assertThat(testNpaReport.getTypeOfCode()).isEqualTo(UPDATED_TYPE_OF_CODE);
        assertThat(testNpaReport.getAssignable()).isEqualTo(DEFAULT_ASSIGNABLE);
        assertThat(testNpaReport.getExplanation()).isEqualTo(UPDATED_EXPLANATION);
        assertThat(testNpaReport.getReserved()).isEqualTo(DEFAULT_RESERVED);
        assertThat(testNpaReport.getAssigned()).isEqualTo(DEFAULT_ASSIGNED);
        assertThat(testNpaReport.getAssignmentDate()).isEqualTo(DEFAULT_ASSIGNMENT_DATE);
        assertThat(testNpaReport.getUseType()).isEqualTo(DEFAULT_USE_TYPE);
        assertThat(testNpaReport.getLocation()).isEqualTo(UPDATED_LOCATION);
        assertThat(testNpaReport.getCountry()).isEqualTo(DEFAULT_COUNTRY);
        assertThat(testNpaReport.getInService()).isEqualTo(UPDATED_IN_SERVICE);
        assertThat(testNpaReport.getInServiceDate()).isEqualTo(UPDATED_IN_SERVICE_DATE);
        assertThat(testNpaReport.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testNpaReport.getPlanningLetters()).isEqualTo(DEFAULT_PLANNING_LETTERS);
        assertThat(testNpaReport.getNotes()).isEqualTo(UPDATED_NOTES);
        assertThat(testNpaReport.getOverlay()).isEqualTo(UPDATED_OVERLAY);
        assertThat(testNpaReport.getOverlayComplex()).isEqualTo(UPDATED_OVERLAY_COMPLEX);
        assertThat(testNpaReport.getParentNpaId()).isEqualTo(UPDATED_PARENT_NPA_ID);
        assertThat(testNpaReport.getService()).isEqualTo(DEFAULT_SERVICE);
        assertThat(testNpaReport.getTimeZone()).isEqualTo(DEFAULT_TIME_ZONE);
        assertThat(testNpaReport.getAreaServed()).isEqualTo(UPDATED_AREA_SERVED);
        assertThat(testNpaReport.getMap()).isEqualTo(DEFAULT_MAP);
        assertThat(testNpaReport.getInJeopardy()).isEqualTo(UPDATED_IN_JEOPARDY);
        assertThat(testNpaReport.getReliefPlanningInProgress()).isEqualTo(UPDATED_RELIEF_PLANNING_IN_PROGRESS);
        assertThat(testNpaReport.getHomeNpaLocalCalls()).isEqualTo(UPDATED_HOME_NPA_LOCAL_CALLS);
        assertThat(testNpaReport.getHomeNpaTollCalls()).isEqualTo(DEFAULT_HOME_NPA_TOLL_CALLS);
        assertThat(testNpaReport.getForeignNpaLocalCalls()).isEqualTo(DEFAULT_FOREIGN_NPA_LOCAL_CALLS);
        assertThat(testNpaReport.getForeignNpaTollCalls()).isEqualTo(DEFAULT_FOREIGN_NPA_TOLL_CALLS);
        assertThat(testNpaReport.getPermHnpaLocalCalls()).isEqualTo(DEFAULT_PERM_HNPA_LOCAL_CALLS);
        assertThat(testNpaReport.getPermHnpaTollCalls()).isEqualTo(DEFAULT_PERM_HNPA_TOLL_CALLS);
        assertThat(testNpaReport.getPermHnpaForeignLocalCalls()).isEqualTo(UPDATED_PERM_HNPA_FOREIGN_LOCAL_CALLS);
        assertThat(testNpaReport.getDialingPlanNotes()).isEqualTo(UPDATED_DIALING_PLAN_NOTES);
    }

    @Test
    @Transactional
    void fullUpdateNpaReportWithPatch() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        int databaseSizeBeforeUpdate = npaReportRepository.findAll().size();

        // Update the npaReport using partial update
        NpaReport partialUpdatedNpaReport = new NpaReport();
        partialUpdatedNpaReport.setId(npaReport.getId());

        partialUpdatedNpaReport
            .npaId(UPDATED_NPA_ID)
            .typeOfCode(UPDATED_TYPE_OF_CODE)
            .assignable(UPDATED_ASSIGNABLE)
            .explanation(UPDATED_EXPLANATION)
            .reserved(UPDATED_RESERVED)
            .assigned(UPDATED_ASSIGNED)
            .assignmentDate(UPDATED_ASSIGNMENT_DATE)
            .useType(UPDATED_USE_TYPE)
            .location(UPDATED_LOCATION)
            .country(UPDATED_COUNTRY)
            .inService(UPDATED_IN_SERVICE)
            .inServiceDate(UPDATED_IN_SERVICE_DATE)
            .status(UPDATED_STATUS)
            .planningLetters(UPDATED_PLANNING_LETTERS)
            .notes(UPDATED_NOTES)
            .overlay(UPDATED_OVERLAY)
            .overlayComplex(UPDATED_OVERLAY_COMPLEX)
            .parentNpaId(UPDATED_PARENT_NPA_ID)
            .service(UPDATED_SERVICE)
            .timeZone(UPDATED_TIME_ZONE)
            .areaServed(UPDATED_AREA_SERVED)
            .map(UPDATED_MAP)
            .inJeopardy(UPDATED_IN_JEOPARDY)
            .reliefPlanningInProgress(UPDATED_RELIEF_PLANNING_IN_PROGRESS)
            .homeNpaLocalCalls(UPDATED_HOME_NPA_LOCAL_CALLS)
            .homeNpaTollCalls(UPDATED_HOME_NPA_TOLL_CALLS)
            .foreignNpaLocalCalls(UPDATED_FOREIGN_NPA_LOCAL_CALLS)
            .foreignNpaTollCalls(UPDATED_FOREIGN_NPA_TOLL_CALLS)
            .permHnpaLocalCalls(UPDATED_PERM_HNPA_LOCAL_CALLS)
            .permHnpaTollCalls(UPDATED_PERM_HNPA_TOLL_CALLS)
            .permHnpaForeignLocalCalls(UPDATED_PERM_HNPA_FOREIGN_LOCAL_CALLS)
            .dialingPlanNotes(UPDATED_DIALING_PLAN_NOTES);

        restNpaReportMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedNpaReport.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedNpaReport))
            )
            .andExpect(status().isOk());

        // Validate the NpaReport in the database
        List<NpaReport> npaReportList = npaReportRepository.findAll();
        assertThat(npaReportList).hasSize(databaseSizeBeforeUpdate);
        NpaReport testNpaReport = npaReportList.get(npaReportList.size() - 1);
        assertThat(testNpaReport.getNpaId()).isEqualTo(UPDATED_NPA_ID);
        assertThat(testNpaReport.getTypeOfCode()).isEqualTo(UPDATED_TYPE_OF_CODE);
        assertThat(testNpaReport.getAssignable()).isEqualTo(UPDATED_ASSIGNABLE);
        assertThat(testNpaReport.getExplanation()).isEqualTo(UPDATED_EXPLANATION);
        assertThat(testNpaReport.getReserved()).isEqualTo(UPDATED_RESERVED);
        assertThat(testNpaReport.getAssigned()).isEqualTo(UPDATED_ASSIGNED);
        assertThat(testNpaReport.getAssignmentDate()).isEqualTo(UPDATED_ASSIGNMENT_DATE);
        assertThat(testNpaReport.getUseType()).isEqualTo(UPDATED_USE_TYPE);
        assertThat(testNpaReport.getLocation()).isEqualTo(UPDATED_LOCATION);
        assertThat(testNpaReport.getCountry()).isEqualTo(UPDATED_COUNTRY);
        assertThat(testNpaReport.getInService()).isEqualTo(UPDATED_IN_SERVICE);
        assertThat(testNpaReport.getInServiceDate()).isEqualTo(UPDATED_IN_SERVICE_DATE);
        assertThat(testNpaReport.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testNpaReport.getPlanningLetters()).isEqualTo(UPDATED_PLANNING_LETTERS);
        assertThat(testNpaReport.getNotes()).isEqualTo(UPDATED_NOTES);
        assertThat(testNpaReport.getOverlay()).isEqualTo(UPDATED_OVERLAY);
        assertThat(testNpaReport.getOverlayComplex()).isEqualTo(UPDATED_OVERLAY_COMPLEX);
        assertThat(testNpaReport.getParentNpaId()).isEqualTo(UPDATED_PARENT_NPA_ID);
        assertThat(testNpaReport.getService()).isEqualTo(UPDATED_SERVICE);
        assertThat(testNpaReport.getTimeZone()).isEqualTo(UPDATED_TIME_ZONE);
        assertThat(testNpaReport.getAreaServed()).isEqualTo(UPDATED_AREA_SERVED);
        assertThat(testNpaReport.getMap()).isEqualTo(UPDATED_MAP);
        assertThat(testNpaReport.getInJeopardy()).isEqualTo(UPDATED_IN_JEOPARDY);
        assertThat(testNpaReport.getReliefPlanningInProgress()).isEqualTo(UPDATED_RELIEF_PLANNING_IN_PROGRESS);
        assertThat(testNpaReport.getHomeNpaLocalCalls()).isEqualTo(UPDATED_HOME_NPA_LOCAL_CALLS);
        assertThat(testNpaReport.getHomeNpaTollCalls()).isEqualTo(UPDATED_HOME_NPA_TOLL_CALLS);
        assertThat(testNpaReport.getForeignNpaLocalCalls()).isEqualTo(UPDATED_FOREIGN_NPA_LOCAL_CALLS);
        assertThat(testNpaReport.getForeignNpaTollCalls()).isEqualTo(UPDATED_FOREIGN_NPA_TOLL_CALLS);
        assertThat(testNpaReport.getPermHnpaLocalCalls()).isEqualTo(UPDATED_PERM_HNPA_LOCAL_CALLS);
        assertThat(testNpaReport.getPermHnpaTollCalls()).isEqualTo(UPDATED_PERM_HNPA_TOLL_CALLS);
        assertThat(testNpaReport.getPermHnpaForeignLocalCalls()).isEqualTo(UPDATED_PERM_HNPA_FOREIGN_LOCAL_CALLS);
        assertThat(testNpaReport.getDialingPlanNotes()).isEqualTo(UPDATED_DIALING_PLAN_NOTES);
    }

    @Test
    @Transactional
    void patchNonExistingNpaReport() throws Exception {
        int databaseSizeBeforeUpdate = npaReportRepository.findAll().size();
        npaReport.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restNpaReportMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, npaReport.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(npaReport))
            )
            .andExpect(status().isBadRequest());

        // Validate the NpaReport in the database
        List<NpaReport> npaReportList = npaReportRepository.findAll();
        assertThat(npaReportList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchNpaReport() throws Exception {
        int databaseSizeBeforeUpdate = npaReportRepository.findAll().size();
        npaReport.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restNpaReportMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(npaReport))
            )
            .andExpect(status().isBadRequest());

        // Validate the NpaReport in the database
        List<NpaReport> npaReportList = npaReportRepository.findAll();
        assertThat(npaReportList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamNpaReport() throws Exception {
        int databaseSizeBeforeUpdate = npaReportRepository.findAll().size();
        npaReport.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restNpaReportMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(npaReport))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the NpaReport in the database
        List<NpaReport> npaReportList = npaReportRepository.findAll();
        assertThat(npaReportList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteNpaReport() throws Exception {
        // Initialize the database
        npaReportRepository.saveAndFlush(npaReport);

        int databaseSizeBeforeDelete = npaReportRepository.findAll().size();

        // Delete the npaReport
        restNpaReportMockMvc
            .perform(delete(ENTITY_API_URL_ID, npaReport.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<NpaReport> npaReportList = npaReportRepository.findAll();
        assertThat(npaReportList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
