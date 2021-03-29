import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './npa-report.reducer';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface INpaReportDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const NpaReportDetail = (props: INpaReportDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { npaReportEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="npaReportDetailsHeading">NpaReport</h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">ID</span>
          </dt>
          <dd>{npaReportEntity.id}</dd>
          <dt>
            <span id="npaId">Npa Id</span>
          </dt>
          <dd>{npaReportEntity.npaId}</dd>
          <dt>
            <span id="typeOfCode">Type Of Code</span>
          </dt>
          <dd>{npaReportEntity.typeOfCode}</dd>
          <dt>
            <span id="assignable">Assignable</span>
          </dt>
          <dd>{npaReportEntity.assignable}</dd>
          <dt>
            <span id="explanation">Explanation</span>
          </dt>
          <dd>{npaReportEntity.explanation}</dd>
          <dt>
            <span id="reserved">Reserved</span>
          </dt>
          <dd>{npaReportEntity.reserved}</dd>
          <dt>
            <span id="assigned">Assigned</span>
          </dt>
          <dd>{npaReportEntity.assigned}</dd>
          <dt>
            <span id="assignmentDate">Assignment Date</span>
          </dt>
          <dd>
            {npaReportEntity.assignmentDate ? (
              <TextFormat value={npaReportEntity.assignmentDate} type="date" format={APP_LOCAL_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="useType">Use Type</span>
          </dt>
          <dd>{npaReportEntity.useType}</dd>
          <dt>
            <span id="location">Location</span>
          </dt>
          <dd>{npaReportEntity.location}</dd>
          <dt>
            <span id="country">Country</span>
          </dt>
          <dd>{npaReportEntity.country}</dd>
          <dt>
            <span id="inService">In Service</span>
          </dt>
          <dd>{npaReportEntity.inService}</dd>
          <dt>
            <span id="inServiceDate">In Service Date</span>
          </dt>
          <dd>
            {npaReportEntity.inServiceDate ? (
              <TextFormat value={npaReportEntity.inServiceDate} type="date" format={APP_LOCAL_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="status">Status</span>
          </dt>
          <dd>{npaReportEntity.status}</dd>
          <dt>
            <span id="planningLetters">Planning Letters</span>
          </dt>
          <dd>{npaReportEntity.planningLetters}</dd>
          <dt>
            <span id="notes">Notes</span>
          </dt>
          <dd>{npaReportEntity.notes}</dd>
          <dt>
            <span id="overlay">Overlay</span>
          </dt>
          <dd>{npaReportEntity.overlay}</dd>
          <dt>
            <span id="overlayComplex">Overlay Complex</span>
          </dt>
          <dd>{npaReportEntity.overlayComplex}</dd>
          <dt>
            <span id="parentNpaId">Parent Npa Id</span>
          </dt>
          <dd>{npaReportEntity.parentNpaId}</dd>
          <dt>
            <span id="service">Service</span>
          </dt>
          <dd>{npaReportEntity.service}</dd>
          <dt>
            <span id="timeZone">Time Zone</span>
          </dt>
          <dd>{npaReportEntity.timeZone}</dd>
          <dt>
            <span id="areaServed">Area Served</span>
          </dt>
          <dd>{npaReportEntity.areaServed}</dd>
          <dt>
            <span id="map">Map</span>
          </dt>
          <dd>{npaReportEntity.map}</dd>
          <dt>
            <span id="inJeopardy">In Jeopardy</span>
          </dt>
          <dd>{npaReportEntity.inJeopardy}</dd>
          <dt>
            <span id="reliefPlanningInProgress">Relief Planning In Progress</span>
          </dt>
          <dd>{npaReportEntity.reliefPlanningInProgress}</dd>
          <dt>
            <span id="homeNpaLocalCalls">Home Npa Local Calls</span>
          </dt>
          <dd>{npaReportEntity.homeNpaLocalCalls}</dd>
          <dt>
            <span id="homeNpaTollCalls">Home Npa Toll Calls</span>
          </dt>
          <dd>{npaReportEntity.homeNpaTollCalls}</dd>
          <dt>
            <span id="foreignNpaLocalCalls">Foreign Npa Local Calls</span>
          </dt>
          <dd>{npaReportEntity.foreignNpaLocalCalls}</dd>
          <dt>
            <span id="foreignNpaTollCalls">Foreign Npa Toll Calls</span>
          </dt>
          <dd>{npaReportEntity.foreignNpaTollCalls}</dd>
          <dt>
            <span id="permHnpaLocalCalls">Perm Hnpa Local Calls</span>
          </dt>
          <dd>{npaReportEntity.permHnpaLocalCalls}</dd>
          <dt>
            <span id="permHnpaTollCalls">Perm Hnpa Toll Calls</span>
          </dt>
          <dd>{npaReportEntity.permHnpaTollCalls}</dd>
          <dt>
            <span id="permHnpaForeignLocalCalls">Perm Hnpa Foreign Local Calls</span>
          </dt>
          <dd>{npaReportEntity.permHnpaForeignLocalCalls}</dd>
          <dt>
            <span id="dialingPlanNotes">Dialing Plan Notes</span>
          </dt>
          <dd>{npaReportEntity.dialingPlanNotes}</dd>
        </dl>
        <Button tag={Link} to="/npa-report" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/npa-report/${npaReportEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ npaReport }: IRootState) => ({
  npaReportEntity: npaReport.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(NpaReportDetail);
