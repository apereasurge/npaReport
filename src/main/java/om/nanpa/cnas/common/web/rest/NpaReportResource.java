package om.nanpa.cnas.common.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import om.nanpa.cnas.common.domain.NpaReport;
import om.nanpa.cnas.common.repository.NpaReportRepository;
import om.nanpa.cnas.common.service.NpaReportQueryService;
import om.nanpa.cnas.common.service.NpaReportService;
import om.nanpa.cnas.common.service.criteria.NpaReportCriteria;
import om.nanpa.cnas.common.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link om.nanpa.cnas.common.domain.NpaReport}.
 */
@RestController
@RequestMapping("/api")
public class NpaReportResource {

    private final Logger log = LoggerFactory.getLogger(NpaReportResource.class);

    private static final String ENTITY_NAME = "npaReport";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final NpaReportService npaReportService;

    private final NpaReportRepository npaReportRepository;

    private final NpaReportQueryService npaReportQueryService;

    public NpaReportResource(
        NpaReportService npaReportService,
        NpaReportRepository npaReportRepository,
        NpaReportQueryService npaReportQueryService
    ) {
        this.npaReportService = npaReportService;
        this.npaReportRepository = npaReportRepository;
        this.npaReportQueryService = npaReportQueryService;
    }

    /**
     * {@code POST  /npa-reports} : Create a new npaReport.
     *
     * @param npaReport the npaReport to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new npaReport, or with status {@code 400 (Bad Request)} if the npaReport has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/npa-reports")
    public ResponseEntity<NpaReport> createNpaReport(@RequestBody NpaReport npaReport) throws URISyntaxException {
        log.debug("REST request to save NpaReport : {}", npaReport);
        if (npaReport.getId() != null) {
            throw new BadRequestAlertException("A new npaReport cannot already have an ID", ENTITY_NAME, "idexists");
        }
        NpaReport result = npaReportService.save(npaReport);
        return ResponseEntity
            .created(new URI("/api/npa-reports/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /npa-reports/:id} : Updates an existing npaReport.
     *
     * @param id the id of the npaReport to save.
     * @param npaReport the npaReport to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated npaReport,
     * or with status {@code 400 (Bad Request)} if the npaReport is not valid,
     * or with status {@code 500 (Internal Server Error)} if the npaReport couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/npa-reports/{id}")
    public ResponseEntity<NpaReport> updateNpaReport(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody NpaReport npaReport
    ) throws URISyntaxException {
        log.debug("REST request to update NpaReport : {}, {}", id, npaReport);
        if (npaReport.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, npaReport.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!npaReportRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        NpaReport result = npaReportService.save(npaReport);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, npaReport.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /npa-reports/:id} : Partial updates given fields of an existing npaReport, field will ignore if it is null
     *
     * @param id the id of the npaReport to save.
     * @param npaReport the npaReport to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated npaReport,
     * or with status {@code 400 (Bad Request)} if the npaReport is not valid,
     * or with status {@code 404 (Not Found)} if the npaReport is not found,
     * or with status {@code 500 (Internal Server Error)} if the npaReport couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/npa-reports/{id}", consumes = "application/merge-patch+json")
    public ResponseEntity<NpaReport> partialUpdateNpaReport(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody NpaReport npaReport
    ) throws URISyntaxException {
        log.debug("REST request to partial update NpaReport partially : {}, {}", id, npaReport);
        if (npaReport.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, npaReport.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!npaReportRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<NpaReport> result = npaReportService.partialUpdate(npaReport);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, npaReport.getId().toString())
        );
    }

    /**
     * {@code GET  /npa-reports} : get all the npaReports.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of npaReports in body.
     */
    @GetMapping("/npa-reports")
    public ResponseEntity<List<NpaReport>> getAllNpaReports(NpaReportCriteria criteria) {
        log.debug("REST request to get NpaReports by criteria: {}", criteria);
        List<NpaReport> entityList = npaReportQueryService.findByCriteria(criteria);
        return ResponseEntity.ok().body(entityList);
    }

    /**
     * {@code GET  /npa-reports/count} : count all the npaReports.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/npa-reports/count")
    public ResponseEntity<Long> countNpaReports(NpaReportCriteria criteria) {
        log.debug("REST request to count NpaReports by criteria: {}", criteria);
        return ResponseEntity.ok().body(npaReportQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /npa-reports/:id} : get the "id" npaReport.
     *
     * @param id the id of the npaReport to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the npaReport, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/npa-reports/{id}")
    public ResponseEntity<NpaReport> getNpaReport(@PathVariable Long id) {
        log.debug("REST request to get NpaReport : {}", id);
        Optional<NpaReport> npaReport = npaReportService.findOne(id);
        return ResponseUtil.wrapOrNotFound(npaReport);
    }

    /**
     * {@code DELETE  /npa-reports/:id} : delete the "id" npaReport.
     *
     * @param id the id of the npaReport to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/npa-reports/{id}")
    public ResponseEntity<Void> deleteNpaReport(@PathVariable Long id) {
        log.debug("REST request to delete NpaReport : {}", id);
        npaReportService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
