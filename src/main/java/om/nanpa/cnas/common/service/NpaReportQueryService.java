package om.nanpa.cnas.common.service;

import java.util.List;
import javax.persistence.criteria.JoinType;
import om.nanpa.cnas.common.domain.*; // for static metamodels
import om.nanpa.cnas.common.domain.NpaReport;
import om.nanpa.cnas.common.repository.NpaReportRepository;
import om.nanpa.cnas.common.service.criteria.NpaReportCriteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;

/**
 * Service for executing complex queries for {@link NpaReport} entities in the database.
 * The main input is a {@link NpaReportCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link NpaReport} or a {@link Page} of {@link NpaReport} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class NpaReportQueryService extends QueryService<NpaReport> {

    private final Logger log = LoggerFactory.getLogger(NpaReportQueryService.class);

    private final NpaReportRepository npaReportRepository;

    public NpaReportQueryService(NpaReportRepository npaReportRepository) {
        this.npaReportRepository = npaReportRepository;
    }

    /**
     * Return a {@link List} of {@link NpaReport} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<NpaReport> findByCriteria(NpaReportCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<NpaReport> specification = createSpecification(criteria);
        return npaReportRepository.findAll(specification);
    }

    /**
     * Return a {@link Page} of {@link NpaReport} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<NpaReport> findByCriteria(NpaReportCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<NpaReport> specification = createSpecification(criteria);
        return npaReportRepository.findAll(specification, page);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(NpaReportCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<NpaReport> specification = createSpecification(criteria);
        return npaReportRepository.count(specification);
    }

    /**
     * Function to convert {@link NpaReportCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<NpaReport> createSpecification(NpaReportCriteria criteria) {
        Specification<NpaReport> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), NpaReport_.id));
            }
            if (criteria.getNpaId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNpaId(), NpaReport_.npaId));
            }
            if (criteria.getTypeOfCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTypeOfCode(), NpaReport_.typeOfCode));
            }
            if (criteria.getAssignable() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAssignable(), NpaReport_.assignable));
            }
            if (criteria.getExplanation() != null) {
                specification = specification.and(buildStringSpecification(criteria.getExplanation(), NpaReport_.explanation));
            }
            if (criteria.getReserved() != null) {
                specification = specification.and(buildStringSpecification(criteria.getReserved(), NpaReport_.reserved));
            }
            if (criteria.getAssigned() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAssigned(), NpaReport_.assigned));
            }
            if (criteria.getAssignmentDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getAssignmentDate(), NpaReport_.assignmentDate));
            }
            if (criteria.getUseType() != null) {
                specification = specification.and(buildStringSpecification(criteria.getUseType(), NpaReport_.useType));
            }
            if (criteria.getLocation() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLocation(), NpaReport_.location));
            }
            if (criteria.getCountry() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCountry(), NpaReport_.country));
            }
            if (criteria.getInService() != null) {
                specification = specification.and(buildStringSpecification(criteria.getInService(), NpaReport_.inService));
            }
            if (criteria.getInServiceDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getInServiceDate(), NpaReport_.inServiceDate));
            }
            if (criteria.getStatus() != null) {
                specification = specification.and(buildStringSpecification(criteria.getStatus(), NpaReport_.status));
            }
            if (criteria.getPlanningLetters() != null) {
                specification = specification.and(buildStringSpecification(criteria.getPlanningLetters(), NpaReport_.planningLetters));
            }
            if (criteria.getNotes() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNotes(), NpaReport_.notes));
            }
            if (criteria.getOverlay() != null) {
                specification = specification.and(buildStringSpecification(criteria.getOverlay(), NpaReport_.overlay));
            }
            if (criteria.getOverlayComplex() != null) {
                specification = specification.and(buildStringSpecification(criteria.getOverlayComplex(), NpaReport_.overlayComplex));
            }
            if (criteria.getParentNpaId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getParentNpaId(), NpaReport_.parentNpaId));
            }
            if (criteria.getService() != null) {
                specification = specification.and(buildStringSpecification(criteria.getService(), NpaReport_.service));
            }
            if (criteria.getTimeZone() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTimeZone(), NpaReport_.timeZone));
            }
            if (criteria.getAreaServed() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAreaServed(), NpaReport_.areaServed));
            }
            if (criteria.getMap() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMap(), NpaReport_.map));
            }
            if (criteria.getInJeopardy() != null) {
                specification = specification.and(buildStringSpecification(criteria.getInJeopardy(), NpaReport_.inJeopardy));
            }
            if (criteria.getReliefPlanningInProgress() != null) {
                specification =
                    specification.and(
                        buildStringSpecification(criteria.getReliefPlanningInProgress(), NpaReport_.reliefPlanningInProgress)
                    );
            }
            if (criteria.getHomeNpaLocalCalls() != null) {
                specification = specification.and(buildStringSpecification(criteria.getHomeNpaLocalCalls(), NpaReport_.homeNpaLocalCalls));
            }
            if (criteria.getHomeNpaTollCalls() != null) {
                specification = specification.and(buildStringSpecification(criteria.getHomeNpaTollCalls(), NpaReport_.homeNpaTollCalls));
            }
            if (criteria.getForeignNpaLocalCalls() != null) {
                specification =
                    specification.and(buildStringSpecification(criteria.getForeignNpaLocalCalls(), NpaReport_.foreignNpaLocalCalls));
            }
            if (criteria.getForeignNpaTollCalls() != null) {
                specification =
                    specification.and(buildStringSpecification(criteria.getForeignNpaTollCalls(), NpaReport_.foreignNpaTollCalls));
            }
            if (criteria.getPermHnpaLocalCalls() != null) {
                specification =
                    specification.and(buildStringSpecification(criteria.getPermHnpaLocalCalls(), NpaReport_.permHnpaLocalCalls));
            }
            if (criteria.getPermHnpaTollCalls() != null) {
                specification = specification.and(buildStringSpecification(criteria.getPermHnpaTollCalls(), NpaReport_.permHnpaTollCalls));
            }
            if (criteria.getPermHnpaForeignLocalCalls() != null) {
                specification =
                    specification.and(
                        buildStringSpecification(criteria.getPermHnpaForeignLocalCalls(), NpaReport_.permHnpaForeignLocalCalls)
                    );
            }
            if (criteria.getDialingPlanNotes() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDialingPlanNotes(), NpaReport_.dialingPlanNotes));
            }
        }
        return specification;
    }
}
