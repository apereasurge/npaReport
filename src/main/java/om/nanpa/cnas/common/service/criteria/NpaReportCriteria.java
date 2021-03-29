package om.nanpa.cnas.common.service.criteria;

import java.io.Serializable;
import java.util.Objects;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.BooleanFilter;
import tech.jhipster.service.filter.DoubleFilter;
import tech.jhipster.service.filter.Filter;
import tech.jhipster.service.filter.FloatFilter;
import tech.jhipster.service.filter.IntegerFilter;
import tech.jhipster.service.filter.LocalDateFilter;
import tech.jhipster.service.filter.LongFilter;
import tech.jhipster.service.filter.StringFilter;

/**
 * Criteria class for the {@link om.nanpa.cnas.common.domain.NpaReport} entity. This class is used
 * in {@link om.nanpa.cnas.common.web.rest.NpaReportResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /npa-reports?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class NpaReportCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private LongFilter npaId;

    private StringFilter typeOfCode;

    private StringFilter assignable;

    private StringFilter explanation;

    private StringFilter reserved;

    private StringFilter assigned;

    private LocalDateFilter assignmentDate;

    private StringFilter useType;

    private StringFilter location;

    private StringFilter country;

    private StringFilter inService;

    private LocalDateFilter inServiceDate;

    private StringFilter status;

    private StringFilter planningLetters;

    private StringFilter notes;

    private StringFilter overlay;

    private StringFilter overlayComplex;

    private LongFilter parentNpaId;

    private StringFilter service;

    private StringFilter timeZone;

    private StringFilter areaServed;

    private StringFilter map;

    private StringFilter inJeopardy;

    private StringFilter reliefPlanningInProgress;

    private StringFilter homeNpaLocalCalls;

    private StringFilter homeNpaTollCalls;

    private StringFilter foreignNpaLocalCalls;

    private StringFilter foreignNpaTollCalls;

    private StringFilter permHnpaLocalCalls;

    private StringFilter permHnpaTollCalls;

    private StringFilter permHnpaForeignLocalCalls;

    private StringFilter dialingPlanNotes;

    public NpaReportCriteria() {}

    public NpaReportCriteria(NpaReportCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.npaId = other.npaId == null ? null : other.npaId.copy();
        this.typeOfCode = other.typeOfCode == null ? null : other.typeOfCode.copy();
        this.assignable = other.assignable == null ? null : other.assignable.copy();
        this.explanation = other.explanation == null ? null : other.explanation.copy();
        this.reserved = other.reserved == null ? null : other.reserved.copy();
        this.assigned = other.assigned == null ? null : other.assigned.copy();
        this.assignmentDate = other.assignmentDate == null ? null : other.assignmentDate.copy();
        this.useType = other.useType == null ? null : other.useType.copy();
        this.location = other.location == null ? null : other.location.copy();
        this.country = other.country == null ? null : other.country.copy();
        this.inService = other.inService == null ? null : other.inService.copy();
        this.inServiceDate = other.inServiceDate == null ? null : other.inServiceDate.copy();
        this.status = other.status == null ? null : other.status.copy();
        this.planningLetters = other.planningLetters == null ? null : other.planningLetters.copy();
        this.notes = other.notes == null ? null : other.notes.copy();
        this.overlay = other.overlay == null ? null : other.overlay.copy();
        this.overlayComplex = other.overlayComplex == null ? null : other.overlayComplex.copy();
        this.parentNpaId = other.parentNpaId == null ? null : other.parentNpaId.copy();
        this.service = other.service == null ? null : other.service.copy();
        this.timeZone = other.timeZone == null ? null : other.timeZone.copy();
        this.areaServed = other.areaServed == null ? null : other.areaServed.copy();
        this.map = other.map == null ? null : other.map.copy();
        this.inJeopardy = other.inJeopardy == null ? null : other.inJeopardy.copy();
        this.reliefPlanningInProgress = other.reliefPlanningInProgress == null ? null : other.reliefPlanningInProgress.copy();
        this.homeNpaLocalCalls = other.homeNpaLocalCalls == null ? null : other.homeNpaLocalCalls.copy();
        this.homeNpaTollCalls = other.homeNpaTollCalls == null ? null : other.homeNpaTollCalls.copy();
        this.foreignNpaLocalCalls = other.foreignNpaLocalCalls == null ? null : other.foreignNpaLocalCalls.copy();
        this.foreignNpaTollCalls = other.foreignNpaTollCalls == null ? null : other.foreignNpaTollCalls.copy();
        this.permHnpaLocalCalls = other.permHnpaLocalCalls == null ? null : other.permHnpaLocalCalls.copy();
        this.permHnpaTollCalls = other.permHnpaTollCalls == null ? null : other.permHnpaTollCalls.copy();
        this.permHnpaForeignLocalCalls = other.permHnpaForeignLocalCalls == null ? null : other.permHnpaForeignLocalCalls.copy();
        this.dialingPlanNotes = other.dialingPlanNotes == null ? null : other.dialingPlanNotes.copy();
    }

    @Override
    public NpaReportCriteria copy() {
        return new NpaReportCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public LongFilter id() {
        if (id == null) {
            id = new LongFilter();
        }
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public LongFilter getNpaId() {
        return npaId;
    }

    public LongFilter npaId() {
        if (npaId == null) {
            npaId = new LongFilter();
        }
        return npaId;
    }

    public void setNpaId(LongFilter npaId) {
        this.npaId = npaId;
    }

    public StringFilter getTypeOfCode() {
        return typeOfCode;
    }

    public StringFilter typeOfCode() {
        if (typeOfCode == null) {
            typeOfCode = new StringFilter();
        }
        return typeOfCode;
    }

    public void setTypeOfCode(StringFilter typeOfCode) {
        this.typeOfCode = typeOfCode;
    }

    public StringFilter getAssignable() {
        return assignable;
    }

    public StringFilter assignable() {
        if (assignable == null) {
            assignable = new StringFilter();
        }
        return assignable;
    }

    public void setAssignable(StringFilter assignable) {
        this.assignable = assignable;
    }

    public StringFilter getExplanation() {
        return explanation;
    }

    public StringFilter explanation() {
        if (explanation == null) {
            explanation = new StringFilter();
        }
        return explanation;
    }

    public void setExplanation(StringFilter explanation) {
        this.explanation = explanation;
    }

    public StringFilter getReserved() {
        return reserved;
    }

    public StringFilter reserved() {
        if (reserved == null) {
            reserved = new StringFilter();
        }
        return reserved;
    }

    public void setReserved(StringFilter reserved) {
        this.reserved = reserved;
    }

    public StringFilter getAssigned() {
        return assigned;
    }

    public StringFilter assigned() {
        if (assigned == null) {
            assigned = new StringFilter();
        }
        return assigned;
    }

    public void setAssigned(StringFilter assigned) {
        this.assigned = assigned;
    }

    public LocalDateFilter getAssignmentDate() {
        return assignmentDate;
    }

    public LocalDateFilter assignmentDate() {
        if (assignmentDate == null) {
            assignmentDate = new LocalDateFilter();
        }
        return assignmentDate;
    }

    public void setAssignmentDate(LocalDateFilter assignmentDate) {
        this.assignmentDate = assignmentDate;
    }

    public StringFilter getUseType() {
        return useType;
    }

    public StringFilter useType() {
        if (useType == null) {
            useType = new StringFilter();
        }
        return useType;
    }

    public void setUseType(StringFilter useType) {
        this.useType = useType;
    }

    public StringFilter getLocation() {
        return location;
    }

    public StringFilter location() {
        if (location == null) {
            location = new StringFilter();
        }
        return location;
    }

    public void setLocation(StringFilter location) {
        this.location = location;
    }

    public StringFilter getCountry() {
        return country;
    }

    public StringFilter country() {
        if (country == null) {
            country = new StringFilter();
        }
        return country;
    }

    public void setCountry(StringFilter country) {
        this.country = country;
    }

    public StringFilter getInService() {
        return inService;
    }

    public StringFilter inService() {
        if (inService == null) {
            inService = new StringFilter();
        }
        return inService;
    }

    public void setInService(StringFilter inService) {
        this.inService = inService;
    }

    public LocalDateFilter getInServiceDate() {
        return inServiceDate;
    }

    public LocalDateFilter inServiceDate() {
        if (inServiceDate == null) {
            inServiceDate = new LocalDateFilter();
        }
        return inServiceDate;
    }

    public void setInServiceDate(LocalDateFilter inServiceDate) {
        this.inServiceDate = inServiceDate;
    }

    public StringFilter getStatus() {
        return status;
    }

    public StringFilter status() {
        if (status == null) {
            status = new StringFilter();
        }
        return status;
    }

    public void setStatus(StringFilter status) {
        this.status = status;
    }

    public StringFilter getPlanningLetters() {
        return planningLetters;
    }

    public StringFilter planningLetters() {
        if (planningLetters == null) {
            planningLetters = new StringFilter();
        }
        return planningLetters;
    }

    public void setPlanningLetters(StringFilter planningLetters) {
        this.planningLetters = planningLetters;
    }

    public StringFilter getNotes() {
        return notes;
    }

    public StringFilter notes() {
        if (notes == null) {
            notes = new StringFilter();
        }
        return notes;
    }

    public void setNotes(StringFilter notes) {
        this.notes = notes;
    }

    public StringFilter getOverlay() {
        return overlay;
    }

    public StringFilter overlay() {
        if (overlay == null) {
            overlay = new StringFilter();
        }
        return overlay;
    }

    public void setOverlay(StringFilter overlay) {
        this.overlay = overlay;
    }

    public StringFilter getOverlayComplex() {
        return overlayComplex;
    }

    public StringFilter overlayComplex() {
        if (overlayComplex == null) {
            overlayComplex = new StringFilter();
        }
        return overlayComplex;
    }

    public void setOverlayComplex(StringFilter overlayComplex) {
        this.overlayComplex = overlayComplex;
    }

    public LongFilter getParentNpaId() {
        return parentNpaId;
    }

    public LongFilter parentNpaId() {
        if (parentNpaId == null) {
            parentNpaId = new LongFilter();
        }
        return parentNpaId;
    }

    public void setParentNpaId(LongFilter parentNpaId) {
        this.parentNpaId = parentNpaId;
    }

    public StringFilter getService() {
        return service;
    }

    public StringFilter service() {
        if (service == null) {
            service = new StringFilter();
        }
        return service;
    }

    public void setService(StringFilter service) {
        this.service = service;
    }

    public StringFilter getTimeZone() {
        return timeZone;
    }

    public StringFilter timeZone() {
        if (timeZone == null) {
            timeZone = new StringFilter();
        }
        return timeZone;
    }

    public void setTimeZone(StringFilter timeZone) {
        this.timeZone = timeZone;
    }

    public StringFilter getAreaServed() {
        return areaServed;
    }

    public StringFilter areaServed() {
        if (areaServed == null) {
            areaServed = new StringFilter();
        }
        return areaServed;
    }

    public void setAreaServed(StringFilter areaServed) {
        this.areaServed = areaServed;
    }

    public StringFilter getMap() {
        return map;
    }

    public StringFilter map() {
        if (map == null) {
            map = new StringFilter();
        }
        return map;
    }

    public void setMap(StringFilter map) {
        this.map = map;
    }

    public StringFilter getInJeopardy() {
        return inJeopardy;
    }

    public StringFilter inJeopardy() {
        if (inJeopardy == null) {
            inJeopardy = new StringFilter();
        }
        return inJeopardy;
    }

    public void setInJeopardy(StringFilter inJeopardy) {
        this.inJeopardy = inJeopardy;
    }

    public StringFilter getReliefPlanningInProgress() {
        return reliefPlanningInProgress;
    }

    public StringFilter reliefPlanningInProgress() {
        if (reliefPlanningInProgress == null) {
            reliefPlanningInProgress = new StringFilter();
        }
        return reliefPlanningInProgress;
    }

    public void setReliefPlanningInProgress(StringFilter reliefPlanningInProgress) {
        this.reliefPlanningInProgress = reliefPlanningInProgress;
    }

    public StringFilter getHomeNpaLocalCalls() {
        return homeNpaLocalCalls;
    }

    public StringFilter homeNpaLocalCalls() {
        if (homeNpaLocalCalls == null) {
            homeNpaLocalCalls = new StringFilter();
        }
        return homeNpaLocalCalls;
    }

    public void setHomeNpaLocalCalls(StringFilter homeNpaLocalCalls) {
        this.homeNpaLocalCalls = homeNpaLocalCalls;
    }

    public StringFilter getHomeNpaTollCalls() {
        return homeNpaTollCalls;
    }

    public StringFilter homeNpaTollCalls() {
        if (homeNpaTollCalls == null) {
            homeNpaTollCalls = new StringFilter();
        }
        return homeNpaTollCalls;
    }

    public void setHomeNpaTollCalls(StringFilter homeNpaTollCalls) {
        this.homeNpaTollCalls = homeNpaTollCalls;
    }

    public StringFilter getForeignNpaLocalCalls() {
        return foreignNpaLocalCalls;
    }

    public StringFilter foreignNpaLocalCalls() {
        if (foreignNpaLocalCalls == null) {
            foreignNpaLocalCalls = new StringFilter();
        }
        return foreignNpaLocalCalls;
    }

    public void setForeignNpaLocalCalls(StringFilter foreignNpaLocalCalls) {
        this.foreignNpaLocalCalls = foreignNpaLocalCalls;
    }

    public StringFilter getForeignNpaTollCalls() {
        return foreignNpaTollCalls;
    }

    public StringFilter foreignNpaTollCalls() {
        if (foreignNpaTollCalls == null) {
            foreignNpaTollCalls = new StringFilter();
        }
        return foreignNpaTollCalls;
    }

    public void setForeignNpaTollCalls(StringFilter foreignNpaTollCalls) {
        this.foreignNpaTollCalls = foreignNpaTollCalls;
    }

    public StringFilter getPermHnpaLocalCalls() {
        return permHnpaLocalCalls;
    }

    public StringFilter permHnpaLocalCalls() {
        if (permHnpaLocalCalls == null) {
            permHnpaLocalCalls = new StringFilter();
        }
        return permHnpaLocalCalls;
    }

    public void setPermHnpaLocalCalls(StringFilter permHnpaLocalCalls) {
        this.permHnpaLocalCalls = permHnpaLocalCalls;
    }

    public StringFilter getPermHnpaTollCalls() {
        return permHnpaTollCalls;
    }

    public StringFilter permHnpaTollCalls() {
        if (permHnpaTollCalls == null) {
            permHnpaTollCalls = new StringFilter();
        }
        return permHnpaTollCalls;
    }

    public void setPermHnpaTollCalls(StringFilter permHnpaTollCalls) {
        this.permHnpaTollCalls = permHnpaTollCalls;
    }

    public StringFilter getPermHnpaForeignLocalCalls() {
        return permHnpaForeignLocalCalls;
    }

    public StringFilter permHnpaForeignLocalCalls() {
        if (permHnpaForeignLocalCalls == null) {
            permHnpaForeignLocalCalls = new StringFilter();
        }
        return permHnpaForeignLocalCalls;
    }

    public void setPermHnpaForeignLocalCalls(StringFilter permHnpaForeignLocalCalls) {
        this.permHnpaForeignLocalCalls = permHnpaForeignLocalCalls;
    }

    public StringFilter getDialingPlanNotes() {
        return dialingPlanNotes;
    }

    public StringFilter dialingPlanNotes() {
        if (dialingPlanNotes == null) {
            dialingPlanNotes = new StringFilter();
        }
        return dialingPlanNotes;
    }

    public void setDialingPlanNotes(StringFilter dialingPlanNotes) {
        this.dialingPlanNotes = dialingPlanNotes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final NpaReportCriteria that = (NpaReportCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(npaId, that.npaId) &&
            Objects.equals(typeOfCode, that.typeOfCode) &&
            Objects.equals(assignable, that.assignable) &&
            Objects.equals(explanation, that.explanation) &&
            Objects.equals(reserved, that.reserved) &&
            Objects.equals(assigned, that.assigned) &&
            Objects.equals(assignmentDate, that.assignmentDate) &&
            Objects.equals(useType, that.useType) &&
            Objects.equals(location, that.location) &&
            Objects.equals(country, that.country) &&
            Objects.equals(inService, that.inService) &&
            Objects.equals(inServiceDate, that.inServiceDate) &&
            Objects.equals(status, that.status) &&
            Objects.equals(planningLetters, that.planningLetters) &&
            Objects.equals(notes, that.notes) &&
            Objects.equals(overlay, that.overlay) &&
            Objects.equals(overlayComplex, that.overlayComplex) &&
            Objects.equals(parentNpaId, that.parentNpaId) &&
            Objects.equals(service, that.service) &&
            Objects.equals(timeZone, that.timeZone) &&
            Objects.equals(areaServed, that.areaServed) &&
            Objects.equals(map, that.map) &&
            Objects.equals(inJeopardy, that.inJeopardy) &&
            Objects.equals(reliefPlanningInProgress, that.reliefPlanningInProgress) &&
            Objects.equals(homeNpaLocalCalls, that.homeNpaLocalCalls) &&
            Objects.equals(homeNpaTollCalls, that.homeNpaTollCalls) &&
            Objects.equals(foreignNpaLocalCalls, that.foreignNpaLocalCalls) &&
            Objects.equals(foreignNpaTollCalls, that.foreignNpaTollCalls) &&
            Objects.equals(permHnpaLocalCalls, that.permHnpaLocalCalls) &&
            Objects.equals(permHnpaTollCalls, that.permHnpaTollCalls) &&
            Objects.equals(permHnpaForeignLocalCalls, that.permHnpaForeignLocalCalls) &&
            Objects.equals(dialingPlanNotes, that.dialingPlanNotes)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            id,
            npaId,
            typeOfCode,
            assignable,
            explanation,
            reserved,
            assigned,
            assignmentDate,
            useType,
            location,
            country,
            inService,
            inServiceDate,
            status,
            planningLetters,
            notes,
            overlay,
            overlayComplex,
            parentNpaId,
            service,
            timeZone,
            areaServed,
            map,
            inJeopardy,
            reliefPlanningInProgress,
            homeNpaLocalCalls,
            homeNpaTollCalls,
            foreignNpaLocalCalls,
            foreignNpaTollCalls,
            permHnpaLocalCalls,
            permHnpaTollCalls,
            permHnpaForeignLocalCalls,
            dialingPlanNotes
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "NpaReportCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (npaId != null ? "npaId=" + npaId + ", " : "") +
            (typeOfCode != null ? "typeOfCode=" + typeOfCode + ", " : "") +
            (assignable != null ? "assignable=" + assignable + ", " : "") +
            (explanation != null ? "explanation=" + explanation + ", " : "") +
            (reserved != null ? "reserved=" + reserved + ", " : "") +
            (assigned != null ? "assigned=" + assigned + ", " : "") +
            (assignmentDate != null ? "assignmentDate=" + assignmentDate + ", " : "") +
            (useType != null ? "useType=" + useType + ", " : "") +
            (location != null ? "location=" + location + ", " : "") +
            (country != null ? "country=" + country + ", " : "") +
            (inService != null ? "inService=" + inService + ", " : "") +
            (inServiceDate != null ? "inServiceDate=" + inServiceDate + ", " : "") +
            (status != null ? "status=" + status + ", " : "") +
            (planningLetters != null ? "planningLetters=" + planningLetters + ", " : "") +
            (notes != null ? "notes=" + notes + ", " : "") +
            (overlay != null ? "overlay=" + overlay + ", " : "") +
            (overlayComplex != null ? "overlayComplex=" + overlayComplex + ", " : "") +
            (parentNpaId != null ? "parentNpaId=" + parentNpaId + ", " : "") +
            (service != null ? "service=" + service + ", " : "") +
            (timeZone != null ? "timeZone=" + timeZone + ", " : "") +
            (areaServed != null ? "areaServed=" + areaServed + ", " : "") +
            (map != null ? "map=" + map + ", " : "") +
            (inJeopardy != null ? "inJeopardy=" + inJeopardy + ", " : "") +
            (reliefPlanningInProgress != null ? "reliefPlanningInProgress=" + reliefPlanningInProgress + ", " : "") +
            (homeNpaLocalCalls != null ? "homeNpaLocalCalls=" + homeNpaLocalCalls + ", " : "") +
            (homeNpaTollCalls != null ? "homeNpaTollCalls=" + homeNpaTollCalls + ", " : "") +
            (foreignNpaLocalCalls != null ? "foreignNpaLocalCalls=" + foreignNpaLocalCalls + ", " : "") +
            (foreignNpaTollCalls != null ? "foreignNpaTollCalls=" + foreignNpaTollCalls + ", " : "") +
            (permHnpaLocalCalls != null ? "permHnpaLocalCalls=" + permHnpaLocalCalls + ", " : "") +
            (permHnpaTollCalls != null ? "permHnpaTollCalls=" + permHnpaTollCalls + ", " : "") +
            (permHnpaForeignLocalCalls != null ? "permHnpaForeignLocalCalls=" + permHnpaForeignLocalCalls + ", " : "") +
            (dialingPlanNotes != null ? "dialingPlanNotes=" + dialingPlanNotes + ", " : "") +
            "}";
    }
}
