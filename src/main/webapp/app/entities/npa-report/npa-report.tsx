import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './npa-report.reducer';
import { INpaReport } from 'app/shared/model/npa-report.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface INpaReportProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const NpaReport = (props: INpaReportProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const handleSyncList = () => {
    props.getEntities();
  };

  const { npaReportList, match, loading } = props;
  return (
    <div>
      <h2 id="npa-report-heading" data-cy="NpaReportHeading">
        Npa Reports
        <div className="d-flex justify-content-end">
          <Button className="mr-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} /> Refresh List
          </Button>
          <Link to={`${match.url}/new`} className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp; Create new Npa Report
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {npaReportList && npaReportList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>ID</th>
                <th>Npa Id</th>
                <th>Type Of Code</th>
                <th>Assignable</th>
                <th>Explanation</th>
                <th>Reserved</th>
                <th>Assigned</th>
                <th>Assignment Date</th>
                <th>Use Type</th>
                <th>Location</th>
                <th>Country</th>
                <th>In Service</th>
                <th>In Service Date</th>
                <th>Status</th>
                <th>Planning Letters</th>
                <th>Notes</th>
                <th>Overlay</th>
                <th>Overlay Complex</th>
                <th>Parent Npa Id</th>
                <th>Service</th>
                <th>Time Zone</th>
                <th>Area Served</th>
                <th>Map</th>
                <th>In Jeopardy</th>
                <th>Relief Planning In Progress</th>
                <th>Home Npa Local Calls</th>
                <th>Home Npa Toll Calls</th>
                <th>Foreign Npa Local Calls</th>
                <th>Foreign Npa Toll Calls</th>
                <th>Perm Hnpa Local Calls</th>
                <th>Perm Hnpa Toll Calls</th>
                <th>Perm Hnpa Foreign Local Calls</th>
                <th>Dialing Plan Notes</th>
                <th />
              </tr>
            </thead>
            <tbody>
              {npaReportList.map((npaReport, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`${match.url}/${npaReport.id}`} color="link" size="sm">
                      {npaReport.id}
                    </Button>
                  </td>
                  <td>{npaReport.id}</td>
                  <td>{npaReport.npaId}</td>
                  <td>{npaReport.typeOfCode}</td>
                  <td>{npaReport.assignable}</td>
                  <td>{npaReport.explanation}</td>
                  <td>{npaReport.reserved}</td>
                  <td>{npaReport.assigned}</td>
                  <td>
                    {npaReport.assignmentDate ? (
                      <TextFormat type="date" value={npaReport.assignmentDate} format={APP_LOCAL_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td>{npaReport.useType}</td>
                  <td>{npaReport.location}</td>
                  <td>{npaReport.country}</td>
                  <td>{npaReport.inService}</td>
                  <td>
                    {npaReport.inServiceDate ? (
                      <TextFormat type="date" value={npaReport.inServiceDate} format={APP_LOCAL_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td>{npaReport.status}</td>
                  <td>{npaReport.planningLetters}</td>
                  <td>{npaReport.notes}</td>
                  <td>{npaReport.overlay}</td>
                  <td>{npaReport.overlayComplex}</td>
                  <td>{npaReport.parentNpaId}</td>
                  <td>{npaReport.service}</td>
                  <td>{npaReport.timeZone}</td>
                  <td>{npaReport.areaServed}</td>
                  <td>{npaReport.map}</td>
                  <td>{npaReport.inJeopardy}</td>
                  <td>{npaReport.reliefPlanningInProgress}</td>
                  <td>{npaReport.homeNpaLocalCalls}</td>
                  <td>{npaReport.homeNpaTollCalls}</td>
                  <td>{npaReport.foreignNpaLocalCalls}</td>
                  <td>{npaReport.foreignNpaTollCalls}</td>
                  <td>{npaReport.permHnpaLocalCalls}</td>
                  <td>{npaReport.permHnpaTollCalls}</td>
                  <td>{npaReport.permHnpaForeignLocalCalls}</td>
                  <td>{npaReport.dialingPlanNotes}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${npaReport.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${npaReport.id}/edit`} color="primary" size="sm" data-cy="entityEditButton">
                        <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${npaReport.id}/delete`} color="danger" size="sm" data-cy="entityDeleteButton">
                        <FontAwesomeIcon icon="trash" /> <span className="d-none d-md-inline">Delete</span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          !loading && <div className="alert alert-warning">No Npa Reports found</div>
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ npaReport }: IRootState) => ({
  npaReportList: npaReport.entities,
  loading: npaReport.loading,
});

const mapDispatchToProps = {
  getEntities,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(NpaReport);
