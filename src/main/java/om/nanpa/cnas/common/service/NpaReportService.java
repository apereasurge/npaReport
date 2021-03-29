package om.nanpa.cnas.common.service;

import java.util.List;
import java.util.Optional;
import om.nanpa.cnas.common.domain.NpaReport;
import om.nanpa.cnas.common.repository.NpaReportRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link NpaReport}.
 */
@Service
@Transactional
public class NpaReportService {

    private final Logger log = LoggerFactory.getLogger(NpaReportService.class);

    private final NpaReportRepository npaReportRepository;

    public NpaReportService(NpaReportRepository npaReportRepository) {
        this.npaReportRepository = npaReportRepository;
    }

    /**
     * Save a npaReport.
     *
     * @param npaReport the entity to save.
     * @return the persisted entity.
     */
    public NpaReport save(NpaReport npaReport) {
        log.debug("Request to save NpaReport : {}", npaReport);
        return npaReportRepository.save(npaReport);
    }

    /**
     * Partially update a npaReport.
     *
     * @param npaReport the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<NpaReport> partialUpdate(NpaReport npaReport) {
        log.debug("Request to partially update NpaReport : {}", npaReport);

        return npaReportRepository
            .findById(npaReport.getId())
            .map(
                existingNpaReport -> {
                    if (npaReport.getNpaId() != null) {
                        existingNpaReport.setNpaId(npaReport.getNpaId());
                    }
                    if (npaReport.getTypeOfCode() != null) {
                        existingNpaReport.setTypeOfCode(npaReport.getTypeOfCode());
                    }
                    if (npaReport.getAssignable() != null) {
                        existingNpaReport.setAssignable(npaReport.getAssignable());
                    }
                    if (npaReport.getExplanation() != null) {
                        existingNpaReport.setExplanation(npaReport.getExplanation());
                    }
                    if (npaReport.getReserved() != null) {
                        existingNpaReport.setReserved(npaReport.getReserved());
                    }
                    if (npaReport.getAssigned() != null) {
                        existingNpaReport.setAssigned(npaReport.getAssigned());
                    }
                    if (npaReport.getAssignmentDate() != null) {
                        existingNpaReport.setAssignmentDate(npaReport.getAssignmentDate());
                    }
                    if (npaReport.getUseType() != null) {
                        existingNpaReport.setUseType(npaReport.getUseType());
                    }
                    if (npaReport.getLocation() != null) {
                        existingNpaReport.setLocation(npaReport.getLocation());
                    }
                    if (npaReport.getCountry() != null) {
                        existingNpaReport.setCountry(npaReport.getCountry());
                    }
                    if (npaReport.getInService() != null) {
                        existingNpaReport.setInService(npaReport.getInService());
                    }
                    if (npaReport.getInServiceDate() != null) {
                        existingNpaReport.setInServiceDate(npaReport.getInServiceDate());
                    }
                    if (npaReport.getStatus() != null) {
                        existingNpaReport.setStatus(npaReport.getStatus());
                    }
                    if (npaReport.getPlanningLetters() != null) {
                        existingNpaReport.setPlanningLetters(npaReport.getPlanningLetters());
                    }
                    if (npaReport.getNotes() != null) {
                        existingNpaReport.setNotes(npaReport.getNotes());
                    }
                    if (npaReport.getOverlay() != null) {
                        existingNpaReport.setOverlay(npaReport.getOverlay());
                    }
                    if (npaReport.getOverlayComplex() != null) {
                        existingNpaReport.setOverlayComplex(npaReport.getOverlayComplex());
                    }
                    if (npaReport.getParentNpaId() != null) {
                        existingNpaReport.setParentNpaId(npaReport.getParentNpaId());
                    }
                    if (npaReport.getService() != null) {
                        existingNpaReport.setService(npaReport.getService());
                    }
                    if (npaReport.getTimeZone() != null) {
                        existingNpaReport.setTimeZone(npaReport.getTimeZone());
                    }
                    if (npaReport.getAreaServed() != null) {
                        existingNpaReport.setAreaServed(npaReport.getAreaServed());
                    }
                    if (npaReport.getMap() != null) {
                        existingNpaReport.setMap(npaReport.getMap());
                    }
                    if (npaReport.getInJeopardy() != null) {
                        existingNpaReport.setInJeopardy(npaReport.getInJeopardy());
                    }
                    if (npaReport.getReliefPlanningInProgress() != null) {
                        existingNpaReport.setReliefPlanningInProgress(npaReport.getReliefPlanningInProgress());
                    }
                    if (npaReport.getHomeNpaLocalCalls() != null) {
                        existingNpaReport.setHomeNpaLocalCalls(npaReport.getHomeNpaLocalCalls());
                    }
                    if (npaReport.getHomeNpaTollCalls() != null) {
                        existingNpaReport.setHomeNpaTollCalls(npaReport.getHomeNpaTollCalls());
                    }
                    if (npaReport.getForeignNpaLocalCalls() != null) {
                        existingNpaReport.setForeignNpaLocalCalls(npaReport.getForeignNpaLocalCalls());
                    }
                    if (npaReport.getForeignNpaTollCalls() != null) {
                        existingNpaReport.setForeignNpaTollCalls(npaReport.getForeignNpaTollCalls());
                    }
                    if (npaReport.getPermHnpaLocalCalls() != null) {
                        existingNpaReport.setPermHnpaLocalCalls(npaReport.getPermHnpaLocalCalls());
                    }
                    if (npaReport.getPermHnpaTollCalls() != null) {
                        existingNpaReport.setPermHnpaTollCalls(npaReport.getPermHnpaTollCalls());
                    }
                    if (npaReport.getPermHnpaForeignLocalCalls() != null) {
                        existingNpaReport.setPermHnpaForeignLocalCalls(npaReport.getPermHnpaForeignLocalCalls());
                    }
                    if (npaReport.getDialingPlanNotes() != null) {
                        existingNpaReport.setDialingPlanNotes(npaReport.getDialingPlanNotes());
                    }

                    return existingNpaReport;
                }
            )
            .map(npaReportRepository::save);
    }

    /**
     * Get all the npaReports.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<NpaReport> findAll() {
        log.debug("Request to get all NpaReports");
        return npaReportRepository.findAll();
    }

    /**
     * Get one npaReport by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<NpaReport> findOne(Long id) {
        log.debug("Request to get NpaReport : {}", id);
        return npaReportRepository.findById(id);
    }

    /**
     * Delete the npaReport by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete NpaReport : {}", id);
        npaReportRepository.deleteById(id);
    }
}
