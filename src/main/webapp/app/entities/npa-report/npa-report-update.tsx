import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './npa-report.reducer';
import { INpaReport } from 'app/shared/model/npa-report.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface INpaReportUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const NpaReportUpdate = (props: INpaReportUpdateProps) => {
  const [isNew] = useState(!props.match.params || !props.match.params.id);

  const { npaReportEntity, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/npa-report');
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }
  }, []);

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const entity = {
        ...npaReportEntity,
        ...values,
      };

      if (isNew) {
        props.createEntity(entity);
      } else {
        props.updateEntity(entity);
      }
    }
  };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="npaReportApp.npaReport.home.createOrEditLabel" data-cy="NpaReportCreateUpdateHeading">
            Create or edit a NpaReport
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : npaReportEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="npa-report-id">ID</Label>
                  <AvInput id="npa-report-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="npaIdLabel" for="npa-report-npaId">
                  Npa Id
                </Label>
                <AvField id="npa-report-npaId" data-cy="npaId" type="string" className="form-control" name="npaId" />
              </AvGroup>
              <AvGroup>
                <Label id="typeOfCodeLabel" for="npa-report-typeOfCode">
                  Type Of Code
                </Label>
                <AvField id="npa-report-typeOfCode" data-cy="typeOfCode" type="text" name="typeOfCode" />
              </AvGroup>
              <AvGroup>
                <Label id="assignableLabel" for="npa-report-assignable">
                  Assignable
                </Label>
                <AvField id="npa-report-assignable" data-cy="assignable" type="text" name="assignable" />
              </AvGroup>
              <AvGroup>
                <Label id="explanationLabel" for="npa-report-explanation">
                  Explanation
                </Label>
                <AvField id="npa-report-explanation" data-cy="explanation" type="text" name="explanation" />
              </AvGroup>
              <AvGroup>
                <Label id="reservedLabel" for="npa-report-reserved">
                  Reserved
                </Label>
                <AvField id="npa-report-reserved" data-cy="reserved" type="text" name="reserved" />
              </AvGroup>
              <AvGroup>
                <Label id="assignedLabel" for="npa-report-assigned">
                  Assigned
                </Label>
                <AvField id="npa-report-assigned" data-cy="assigned" type="text" name="assigned" />
              </AvGroup>
              <AvGroup>
                <Label id="assignmentDateLabel" for="npa-report-assignmentDate">
                  Assignment Date
                </Label>
                <AvField
                  id="npa-report-assignmentDate"
                  data-cy="assignmentDate"
                  type="date"
                  className="form-control"
                  name="assignmentDate"
                />
              </AvGroup>
              <AvGroup>
                <Label id="useTypeLabel" for="npa-report-useType">
                  Use Type
                </Label>
                <AvField id="npa-report-useType" data-cy="useType" type="text" name="useType" />
              </AvGroup>
              <AvGroup>
                <Label id="locationLabel" for="npa-report-location">
                  Location
                </Label>
                <AvField id="npa-report-location" data-cy="location" type="text" name="location" />
              </AvGroup>
              <AvGroup>
                <Label id="countryLabel" for="npa-report-country">
                  Country
                </Label>
                <AvField id="npa-report-country" data-cy="country" type="text" name="country" />
              </AvGroup>
              <AvGroup>
                <Label id="inServiceLabel" for="npa-report-inService">
                  In Service
                </Label>
                <AvField id="npa-report-inService" data-cy="inService" type="text" name="inService" />
              </AvGroup>
              <AvGroup>
                <Label id="inServiceDateLabel" for="npa-report-inServiceDate">
                  In Service Date
                </Label>
                <AvField id="npa-report-inServiceDate" data-cy="inServiceDate" type="date" className="form-control" name="inServiceDate" />
              </AvGroup>
              <AvGroup>
                <Label id="statusLabel" for="npa-report-status">
                  Status
                </Label>
                <AvField id="npa-report-status" data-cy="status" type="text" name="status" />
              </AvGroup>
              <AvGroup>
                <Label id="planningLettersLabel" for="npa-report-planningLetters">
                  Planning Letters
                </Label>
                <AvField id="npa-report-planningLetters" data-cy="planningLetters" type="text" name="planningLetters" />
              </AvGroup>
              <AvGroup>
                <Label id="notesLabel" for="npa-report-notes">
                  Notes
                </Label>
                <AvField id="npa-report-notes" data-cy="notes" type="text" name="notes" />
              </AvGroup>
              <AvGroup>
                <Label id="overlayLabel" for="npa-report-overlay">
                  Overlay
                </Label>
                <AvField id="npa-report-overlay" data-cy="overlay" type="text" name="overlay" />
              </AvGroup>
              <AvGroup>
                <Label id="overlayComplexLabel" for="npa-report-overlayComplex">
                  Overlay Complex
                </Label>
                <AvField id="npa-report-overlayComplex" data-cy="overlayComplex" type="text" name="overlayComplex" />
              </AvGroup>
              <AvGroup>
                <Label id="parentNpaIdLabel" for="npa-report-parentNpaId">
                  Parent Npa Id
                </Label>
                <AvField id="npa-report-parentNpaId" data-cy="parentNpaId" type="string" className="form-control" name="parentNpaId" />
              </AvGroup>
              <AvGroup>
                <Label id="serviceLabel" for="npa-report-service">
                  Service
                </Label>
                <AvField id="npa-report-service" data-cy="service" type="text" name="service" />
              </AvGroup>
              <AvGroup>
                <Label id="timeZoneLabel" for="npa-report-timeZone">
                  Time Zone
                </Label>
                <AvField id="npa-report-timeZone" data-cy="timeZone" type="text" name="timeZone" />
              </AvGroup>
              <AvGroup>
                <Label id="areaServedLabel" for="npa-report-areaServed">
                  Area Served
                </Label>
                <AvField id="npa-report-areaServed" data-cy="areaServed" type="text" name="areaServed" />
              </AvGroup>
              <AvGroup>
                <Label id="mapLabel" for="npa-report-map">
                  Map
                </Label>
                <AvField id="npa-report-map" data-cy="map" type="text" name="map" />
              </AvGroup>
              <AvGroup>
                <Label id="inJeopardyLabel" for="npa-report-inJeopardy">
                  In Jeopardy
                </Label>
                <AvField id="npa-report-inJeopardy" data-cy="inJeopardy" type="text" name="inJeopardy" />
              </AvGroup>
              <AvGroup>
                <Label id="reliefPlanningInProgressLabel" for="npa-report-reliefPlanningInProgress">
                  Relief Planning In Progress
                </Label>
                <AvField
                  id="npa-report-reliefPlanningInProgress"
                  data-cy="reliefPlanningInProgress"
                  type="text"
                  name="reliefPlanningInProgress"
                />
              </AvGroup>
              <AvGroup>
                <Label id="homeNpaLocalCallsLabel" for="npa-report-homeNpaLocalCalls">
                  Home Npa Local Calls
                </Label>
                <AvField id="npa-report-homeNpaLocalCalls" data-cy="homeNpaLocalCalls" type="text" name="homeNpaLocalCalls" />
              </AvGroup>
              <AvGroup>
                <Label id="homeNpaTollCallsLabel" for="npa-report-homeNpaTollCalls">
                  Home Npa Toll Calls
                </Label>
                <AvField id="npa-report-homeNpaTollCalls" data-cy="homeNpaTollCalls" type="text" name="homeNpaTollCalls" />
              </AvGroup>
              <AvGroup>
                <Label id="foreignNpaLocalCallsLabel" for="npa-report-foreignNpaLocalCalls">
                  Foreign Npa Local Calls
                </Label>
                <AvField id="npa-report-foreignNpaLocalCalls" data-cy="foreignNpaLocalCalls" type="text" name="foreignNpaLocalCalls" />
              </AvGroup>
              <AvGroup>
                <Label id="foreignNpaTollCallsLabel" for="npa-report-foreignNpaTollCalls">
                  Foreign Npa Toll Calls
                </Label>
                <AvField id="npa-report-foreignNpaTollCalls" data-cy="foreignNpaTollCalls" type="text" name="foreignNpaTollCalls" />
              </AvGroup>
              <AvGroup>
                <Label id="permHnpaLocalCallsLabel" for="npa-report-permHnpaLocalCalls">
                  Perm Hnpa Local Calls
                </Label>
                <AvField id="npa-report-permHnpaLocalCalls" data-cy="permHnpaLocalCalls" type="text" name="permHnpaLocalCalls" />
              </AvGroup>
              <AvGroup>
                <Label id="permHnpaTollCallsLabel" for="npa-report-permHnpaTollCalls">
                  Perm Hnpa Toll Calls
                </Label>
                <AvField id="npa-report-permHnpaTollCalls" data-cy="permHnpaTollCalls" type="text" name="permHnpaTollCalls" />
              </AvGroup>
              <AvGroup>
                <Label id="permHnpaForeignLocalCallsLabel" for="npa-report-permHnpaForeignLocalCalls">
                  Perm Hnpa Foreign Local Calls
                </Label>
                <AvField
                  id="npa-report-permHnpaForeignLocalCalls"
                  data-cy="permHnpaForeignLocalCalls"
                  type="text"
                  name="permHnpaForeignLocalCalls"
                />
              </AvGroup>
              <AvGroup>
                <Label id="dialingPlanNotesLabel" for="npa-report-dialingPlanNotes">
                  Dialing Plan Notes
                </Label>
                <AvField id="npa-report-dialingPlanNotes" data-cy="dialingPlanNotes" type="text" name="dialingPlanNotes" />
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/npa-report" replace color="info">
                <FontAwesomeIcon icon="arrow-left" />
                &nbsp;
                <span className="d-none d-md-inline">Back</span>
              </Button>
              &nbsp;
              <Button color="primary" id="save-entity" data-cy="entityCreateSaveButton" type="submit" disabled={updating}>
                <FontAwesomeIcon icon="save" />
                &nbsp; Save
              </Button>
            </AvForm>
          )}
        </Col>
      </Row>
    </div>
  );
};

const mapStateToProps = (storeState: IRootState) => ({
  npaReportEntity: storeState.npaReport.entity,
  loading: storeState.npaReport.loading,
  updating: storeState.npaReport.updating,
  updateSuccess: storeState.npaReport.updateSuccess,
});

const mapDispatchToProps = {
  getEntity,
  updateEntity,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(NpaReportUpdate);
