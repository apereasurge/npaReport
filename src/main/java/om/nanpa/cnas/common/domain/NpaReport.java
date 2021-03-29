package om.nanpa.cnas.common.domain;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.*;

/**
 * A NpaReport.
 */
@Entity
@Table(name = "npa_report")
public class NpaReport implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "npa_id")
    private Long npaId;

    @Column(name = "type_of_code")
    private String typeOfCode;

    @Column(name = "assignable")
    private String assignable;

    @Column(name = "explanation")
    private String explanation;

    @Column(name = "reserved")
    private String reserved;

    @Column(name = "assigned")
    private String assigned;

    @Column(name = "assignment_date")
    private LocalDate assignmentDate;

    @Column(name = "use_type")
    private String useType;

    @Column(name = "location")
    private String location;

    @Column(name = "country")
    private String country;

    @Column(name = "in_service")
    private String inService;

    @Column(name = "in_service_date")
    private LocalDate inServiceDate;

    @Column(name = "status")
    private String status;

    @Column(name = "planning_letters")
    private String planningLetters;

    @Column(name = "notes")
    private String notes;

    @Column(name = "overlay")
    private String overlay;

    @Column(name = "overlay_complex")
    private String overlayComplex;

    @Column(name = "parent_npa_id")
    private Long parentNpaId;

    @Column(name = "service")
    private String service;

    @Column(name = "time_zone")
    private String timeZone;

    @Column(name = "area_served")
    private String areaServed;

    @Column(name = "map")
    private String map;

    @Column(name = "in_jeopardy")
    private String inJeopardy;

    @Column(name = "relief_planning_in_progress")
    private String reliefPlanningInProgress;

    @Column(name = "home_npa_local_calls")
    private String homeNpaLocalCalls;

    @Column(name = "home_npa_toll_calls")
    private String homeNpaTollCalls;

    @Column(name = "foreign_npa_local_calls")
    private String foreignNpaLocalCalls;

    @Column(name = "foreign_npa_toll_calls")
    private String foreignNpaTollCalls;

    @Column(name = "perm_hnpa_local_calls")
    private String permHnpaLocalCalls;

    @Column(name = "perm_hnpa_toll_calls")
    private String permHnpaTollCalls;

    @Column(name = "perm_hnpa_foreign_local_calls")
    private String permHnpaForeignLocalCalls;

    @Column(name = "dialing_plan_notes")
    private String dialingPlanNotes;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public NpaReport id(Long id) {
        this.id = id;
        return this;
    }

    public Long getNpaId() {
        return this.npaId;
    }

    public NpaReport npaId(Long npaId) {
        this.npaId = npaId;
        return this;
    }

    public void setNpaId(Long npaId) {
        this.npaId = npaId;
    }

    public String getTypeOfCode() {
        return this.typeOfCode;
    }

    public NpaReport typeOfCode(String typeOfCode) {
        this.typeOfCode = typeOfCode;
        return this;
    }

    public void setTypeOfCode(String typeOfCode) {
        this.typeOfCode = typeOfCode;
    }

    public String getAssignable() {
        return this.assignable;
    }

    public NpaReport assignable(String assignable) {
        this.assignable = assignable;
        return this;
    }

    public void setAssignable(String assignable) {
        this.assignable = assignable;
    }

    public String getExplanation() {
        return this.explanation;
    }

    public NpaReport explanation(String explanation) {
        this.explanation = explanation;
        return this;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public String getReserved() {
        return this.reserved;
    }

    public NpaReport reserved(String reserved) {
        this.reserved = reserved;
        return this;
    }

    public void setReserved(String reserved) {
        this.reserved = reserved;
    }

    public String getAssigned() {
        return this.assigned;
    }

    public NpaReport assigned(String assigned) {
        this.assigned = assigned;
        return this;
    }

    public void setAssigned(String assigned) {
        this.assigned = assigned;
    }

    public LocalDate getAssignmentDate() {
        return this.assignmentDate;
    }

    public NpaReport assignmentDate(LocalDate assignmentDate) {
        this.assignmentDate = assignmentDate;
        return this;
    }

    public void setAssignmentDate(LocalDate assignmentDate) {
        this.assignmentDate = assignmentDate;
    }

    public String getUseType() {
        return this.useType;
    }

    public NpaReport useType(String useType) {
        this.useType = useType;
        return this;
    }

    public void setUseType(String useType) {
        this.useType = useType;
    }

    public String getLocation() {
        return this.location;
    }

    public NpaReport location(String location) {
        this.location = location;
        return this;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCountry() {
        return this.country;
    }

    public NpaReport country(String country) {
        this.country = country;
        return this;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getInService() {
        return this.inService;
    }

    public NpaReport inService(String inService) {
        this.inService = inService;
        return this;
    }

    public void setInService(String inService) {
        this.inService = inService;
    }

    public LocalDate getInServiceDate() {
        return this.inServiceDate;
    }

    public NpaReport inServiceDate(LocalDate inServiceDate) {
        this.inServiceDate = inServiceDate;
        return this;
    }

    public void setInServiceDate(LocalDate inServiceDate) {
        this.inServiceDate = inServiceDate;
    }

    public String getStatus() {
        return this.status;
    }

    public NpaReport status(String status) {
        this.status = status;
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPlanningLetters() {
        return this.planningLetters;
    }

    public NpaReport planningLetters(String planningLetters) {
        this.planningLetters = planningLetters;
        return this;
    }

    public void setPlanningLetters(String planningLetters) {
        this.planningLetters = planningLetters;
    }

    public String getNotes() {
        return this.notes;
    }

    public NpaReport notes(String notes) {
        this.notes = notes;
        return this;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getOverlay() {
        return this.overlay;
    }

    public NpaReport overlay(String overlay) {
        this.overlay = overlay;
        return this;
    }

    public void setOverlay(String overlay) {
        this.overlay = overlay;
    }

    public String getOverlayComplex() {
        return this.overlayComplex;
    }

    public NpaReport overlayComplex(String overlayComplex) {
        this.overlayComplex = overlayComplex;
        return this;
    }

    public void setOverlayComplex(String overlayComplex) {
        this.overlayComplex = overlayComplex;
    }

    public Long getParentNpaId() {
        return this.parentNpaId;
    }

    public NpaReport parentNpaId(Long parentNpaId) {
        this.parentNpaId = parentNpaId;
        return this;
    }

    public void setParentNpaId(Long parentNpaId) {
        this.parentNpaId = parentNpaId;
    }

    public String getService() {
        return this.service;
    }

    public NpaReport service(String service) {
        this.service = service;
        return this;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getTimeZone() {
        return this.timeZone;
    }

    public NpaReport timeZone(String timeZone) {
        this.timeZone = timeZone;
        return this;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public String getAreaServed() {
        return this.areaServed;
    }

    public NpaReport areaServed(String areaServed) {
        this.areaServed = areaServed;
        return this;
    }

    public void setAreaServed(String areaServed) {
        this.areaServed = areaServed;
    }

    public String getMap() {
        return this.map;
    }

    public NpaReport map(String map) {
        this.map = map;
        return this;
    }

    public void setMap(String map) {
        this.map = map;
    }

    public String getInJeopardy() {
        return this.inJeopardy;
    }

    public NpaReport inJeopardy(String inJeopardy) {
        this.inJeopardy = inJeopardy;
        return this;
    }

    public void setInJeopardy(String inJeopardy) {
        this.inJeopardy = inJeopardy;
    }

    public String getReliefPlanningInProgress() {
        return this.reliefPlanningInProgress;
    }

    public NpaReport reliefPlanningInProgress(String reliefPlanningInProgress) {
        this.reliefPlanningInProgress = reliefPlanningInProgress;
        return this;
    }

    public void setReliefPlanningInProgress(String reliefPlanningInProgress) {
        this.reliefPlanningInProgress = reliefPlanningInProgress;
    }

    public String getHomeNpaLocalCalls() {
        return this.homeNpaLocalCalls;
    }

    public NpaReport homeNpaLocalCalls(String homeNpaLocalCalls) {
        this.homeNpaLocalCalls = homeNpaLocalCalls;
        return this;
    }

    public void setHomeNpaLocalCalls(String homeNpaLocalCalls) {
        this.homeNpaLocalCalls = homeNpaLocalCalls;
    }

    public String getHomeNpaTollCalls() {
        return this.homeNpaTollCalls;
    }

    public NpaReport homeNpaTollCalls(String homeNpaTollCalls) {
        this.homeNpaTollCalls = homeNpaTollCalls;
        return this;
    }

    public void setHomeNpaTollCalls(String homeNpaTollCalls) {
        this.homeNpaTollCalls = homeNpaTollCalls;
    }

    public String getForeignNpaLocalCalls() {
        return this.foreignNpaLocalCalls;
    }

    public NpaReport foreignNpaLocalCalls(String foreignNpaLocalCalls) {
        this.foreignNpaLocalCalls = foreignNpaLocalCalls;
        return this;
    }

    public void setForeignNpaLocalCalls(String foreignNpaLocalCalls) {
        this.foreignNpaLocalCalls = foreignNpaLocalCalls;
    }

    public String getForeignNpaTollCalls() {
        return this.foreignNpaTollCalls;
    }

    public NpaReport foreignNpaTollCalls(String foreignNpaTollCalls) {
        this.foreignNpaTollCalls = foreignNpaTollCalls;
        return this;
    }

    public void setForeignNpaTollCalls(String foreignNpaTollCalls) {
        this.foreignNpaTollCalls = foreignNpaTollCalls;
    }

    public String getPermHnpaLocalCalls() {
        return this.permHnpaLocalCalls;
    }

    public NpaReport permHnpaLocalCalls(String permHnpaLocalCalls) {
        this.permHnpaLocalCalls = permHnpaLocalCalls;
        return this;
    }

    public void setPermHnpaLocalCalls(String permHnpaLocalCalls) {
        this.permHnpaLocalCalls = permHnpaLocalCalls;
    }

    public String getPermHnpaTollCalls() {
        return this.permHnpaTollCalls;
    }

    public NpaReport permHnpaTollCalls(String permHnpaTollCalls) {
        this.permHnpaTollCalls = permHnpaTollCalls;
        return this;
    }

    public void setPermHnpaTollCalls(String permHnpaTollCalls) {
        this.permHnpaTollCalls = permHnpaTollCalls;
    }

    public String getPermHnpaForeignLocalCalls() {
        return this.permHnpaForeignLocalCalls;
    }

    public NpaReport permHnpaForeignLocalCalls(String permHnpaForeignLocalCalls) {
        this.permHnpaForeignLocalCalls = permHnpaForeignLocalCalls;
        return this;
    }

    public void setPermHnpaForeignLocalCalls(String permHnpaForeignLocalCalls) {
        this.permHnpaForeignLocalCalls = permHnpaForeignLocalCalls;
    }

    public String getDialingPlanNotes() {
        return this.dialingPlanNotes;
    }

    public NpaReport dialingPlanNotes(String dialingPlanNotes) {
        this.dialingPlanNotes = dialingPlanNotes;
        return this;
    }

    public void setDialingPlanNotes(String dialingPlanNotes) {
        this.dialingPlanNotes = dialingPlanNotes;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof NpaReport)) {
            return false;
        }
        return id != null && id.equals(((NpaReport) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "NpaReport{" +
            "id=" + getId() +
            ", npaId=" + getNpaId() +
            ", typeOfCode='" + getTypeOfCode() + "'" +
            ", assignable='" + getAssignable() + "'" +
            ", explanation='" + getExplanation() + "'" +
            ", reserved='" + getReserved() + "'" +
            ", assigned='" + getAssigned() + "'" +
            ", assignmentDate='" + getAssignmentDate() + "'" +
            ", useType='" + getUseType() + "'" +
            ", location='" + getLocation() + "'" +
            ", country='" + getCountry() + "'" +
            ", inService='" + getInService() + "'" +
            ", inServiceDate='" + getInServiceDate() + "'" +
            ", status='" + getStatus() + "'" +
            ", planningLetters='" + getPlanningLetters() + "'" +
            ", notes='" + getNotes() + "'" +
            ", overlay='" + getOverlay() + "'" +
            ", overlayComplex='" + getOverlayComplex() + "'" +
            ", parentNpaId=" + getParentNpaId() +
            ", service='" + getService() + "'" +
            ", timeZone='" + getTimeZone() + "'" +
            ", areaServed='" + getAreaServed() + "'" +
            ", map='" + getMap() + "'" +
            ", inJeopardy='" + getInJeopardy() + "'" +
            ", reliefPlanningInProgress='" + getReliefPlanningInProgress() + "'" +
            ", homeNpaLocalCalls='" + getHomeNpaLocalCalls() + "'" +
            ", homeNpaTollCalls='" + getHomeNpaTollCalls() + "'" +
            ", foreignNpaLocalCalls='" + getForeignNpaLocalCalls() + "'" +
            ", foreignNpaTollCalls='" + getForeignNpaTollCalls() + "'" +
            ", permHnpaLocalCalls='" + getPermHnpaLocalCalls() + "'" +
            ", permHnpaTollCalls='" + getPermHnpaTollCalls() + "'" +
            ", permHnpaForeignLocalCalls='" + getPermHnpaForeignLocalCalls() + "'" +
            ", dialingPlanNotes='" + getDialingPlanNotes() + "'" +
            "}";
    }
}
