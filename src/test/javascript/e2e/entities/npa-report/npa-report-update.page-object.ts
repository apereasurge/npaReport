import { element, by, ElementFinder } from 'protractor';
import { waitUntilDisplayed, waitUntilHidden, isVisible } from '../../util/utils';

const expect = chai.expect;

export default class NpaReportUpdatePage {
  pageTitle: ElementFinder = element(by.id('npaReportApp.npaReport.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  npaIdInput: ElementFinder = element(by.css('input#npa-report-npaId'));
  typeOfCodeInput: ElementFinder = element(by.css('input#npa-report-typeOfCode'));
  assignableInput: ElementFinder = element(by.css('input#npa-report-assignable'));
  explanationInput: ElementFinder = element(by.css('input#npa-report-explanation'));
  reservedInput: ElementFinder = element(by.css('input#npa-report-reserved'));
  assignedInput: ElementFinder = element(by.css('input#npa-report-assigned'));
  assignmentDateInput: ElementFinder = element(by.css('input#npa-report-assignmentDate'));
  useTypeInput: ElementFinder = element(by.css('input#npa-report-useType'));
  locationInput: ElementFinder = element(by.css('input#npa-report-location'));
  countryInput: ElementFinder = element(by.css('input#npa-report-country'));
  inServiceInput: ElementFinder = element(by.css('input#npa-report-inService'));
  inServiceDateInput: ElementFinder = element(by.css('input#npa-report-inServiceDate'));
  statusInput: ElementFinder = element(by.css('input#npa-report-status'));
  planningLettersInput: ElementFinder = element(by.css('input#npa-report-planningLetters'));
  notesInput: ElementFinder = element(by.css('input#npa-report-notes'));
  overlayInput: ElementFinder = element(by.css('input#npa-report-overlay'));
  overlayComplexInput: ElementFinder = element(by.css('input#npa-report-overlayComplex'));
  parentNpaIdInput: ElementFinder = element(by.css('input#npa-report-parentNpaId'));
  serviceInput: ElementFinder = element(by.css('input#npa-report-service'));
  timeZoneInput: ElementFinder = element(by.css('input#npa-report-timeZone'));
  areaServedInput: ElementFinder = element(by.css('input#npa-report-areaServed'));
  mapInput: ElementFinder = element(by.css('input#npa-report-map'));
  inJeopardyInput: ElementFinder = element(by.css('input#npa-report-inJeopardy'));
  reliefPlanningInProgressInput: ElementFinder = element(by.css('input#npa-report-reliefPlanningInProgress'));
  homeNpaLocalCallsInput: ElementFinder = element(by.css('input#npa-report-homeNpaLocalCalls'));
  homeNpaTollCallsInput: ElementFinder = element(by.css('input#npa-report-homeNpaTollCalls'));
  foreignNpaLocalCallsInput: ElementFinder = element(by.css('input#npa-report-foreignNpaLocalCalls'));
  foreignNpaTollCallsInput: ElementFinder = element(by.css('input#npa-report-foreignNpaTollCalls'));
  permHnpaLocalCallsInput: ElementFinder = element(by.css('input#npa-report-permHnpaLocalCalls'));
  permHnpaTollCallsInput: ElementFinder = element(by.css('input#npa-report-permHnpaTollCalls'));
  permHnpaForeignLocalCallsInput: ElementFinder = element(by.css('input#npa-report-permHnpaForeignLocalCalls'));
  dialingPlanNotesInput: ElementFinder = element(by.css('input#npa-report-dialingPlanNotes'));

  getPageTitle() {
    return this.pageTitle;
  }

  async setNpaIdInput(npaId) {
    await this.npaIdInput.sendKeys(npaId);
  }

  async getNpaIdInput() {
    return this.npaIdInput.getAttribute('value');
  }

  async setTypeOfCodeInput(typeOfCode) {
    await this.typeOfCodeInput.sendKeys(typeOfCode);
  }

  async getTypeOfCodeInput() {
    return this.typeOfCodeInput.getAttribute('value');
  }

  async setAssignableInput(assignable) {
    await this.assignableInput.sendKeys(assignable);
  }

  async getAssignableInput() {
    return this.assignableInput.getAttribute('value');
  }

  async setExplanationInput(explanation) {
    await this.explanationInput.sendKeys(explanation);
  }

  async getExplanationInput() {
    return this.explanationInput.getAttribute('value');
  }

  async setReservedInput(reserved) {
    await this.reservedInput.sendKeys(reserved);
  }

  async getReservedInput() {
    return this.reservedInput.getAttribute('value');
  }

  async setAssignedInput(assigned) {
    await this.assignedInput.sendKeys(assigned);
  }

  async getAssignedInput() {
    return this.assignedInput.getAttribute('value');
  }

  async setAssignmentDateInput(assignmentDate) {
    await this.assignmentDateInput.sendKeys(assignmentDate);
  }

  async getAssignmentDateInput() {
    return this.assignmentDateInput.getAttribute('value');
  }

  async setUseTypeInput(useType) {
    await this.useTypeInput.sendKeys(useType);
  }

  async getUseTypeInput() {
    return this.useTypeInput.getAttribute('value');
  }

  async setLocationInput(location) {
    await this.locationInput.sendKeys(location);
  }

  async getLocationInput() {
    return this.locationInput.getAttribute('value');
  }

  async setCountryInput(country) {
    await this.countryInput.sendKeys(country);
  }

  async getCountryInput() {
    return this.countryInput.getAttribute('value');
  }

  async setInServiceInput(inService) {
    await this.inServiceInput.sendKeys(inService);
  }

  async getInServiceInput() {
    return this.inServiceInput.getAttribute('value');
  }

  async setInServiceDateInput(inServiceDate) {
    await this.inServiceDateInput.sendKeys(inServiceDate);
  }

  async getInServiceDateInput() {
    return this.inServiceDateInput.getAttribute('value');
  }

  async setStatusInput(status) {
    await this.statusInput.sendKeys(status);
  }

  async getStatusInput() {
    return this.statusInput.getAttribute('value');
  }

  async setPlanningLettersInput(planningLetters) {
    await this.planningLettersInput.sendKeys(planningLetters);
  }

  async getPlanningLettersInput() {
    return this.planningLettersInput.getAttribute('value');
  }

  async setNotesInput(notes) {
    await this.notesInput.sendKeys(notes);
  }

  async getNotesInput() {
    return this.notesInput.getAttribute('value');
  }

  async setOverlayInput(overlay) {
    await this.overlayInput.sendKeys(overlay);
  }

  async getOverlayInput() {
    return this.overlayInput.getAttribute('value');
  }

  async setOverlayComplexInput(overlayComplex) {
    await this.overlayComplexInput.sendKeys(overlayComplex);
  }

  async getOverlayComplexInput() {
    return this.overlayComplexInput.getAttribute('value');
  }

  async setParentNpaIdInput(parentNpaId) {
    await this.parentNpaIdInput.sendKeys(parentNpaId);
  }

  async getParentNpaIdInput() {
    return this.parentNpaIdInput.getAttribute('value');
  }

  async setServiceInput(service) {
    await this.serviceInput.sendKeys(service);
  }

  async getServiceInput() {
    return this.serviceInput.getAttribute('value');
  }

  async setTimeZoneInput(timeZone) {
    await this.timeZoneInput.sendKeys(timeZone);
  }

  async getTimeZoneInput() {
    return this.timeZoneInput.getAttribute('value');
  }

  async setAreaServedInput(areaServed) {
    await this.areaServedInput.sendKeys(areaServed);
  }

  async getAreaServedInput() {
    return this.areaServedInput.getAttribute('value');
  }

  async setMapInput(map) {
    await this.mapInput.sendKeys(map);
  }

  async getMapInput() {
    return this.mapInput.getAttribute('value');
  }

  async setInJeopardyInput(inJeopardy) {
    await this.inJeopardyInput.sendKeys(inJeopardy);
  }

  async getInJeopardyInput() {
    return this.inJeopardyInput.getAttribute('value');
  }

  async setReliefPlanningInProgressInput(reliefPlanningInProgress) {
    await this.reliefPlanningInProgressInput.sendKeys(reliefPlanningInProgress);
  }

  async getReliefPlanningInProgressInput() {
    return this.reliefPlanningInProgressInput.getAttribute('value');
  }

  async setHomeNpaLocalCallsInput(homeNpaLocalCalls) {
    await this.homeNpaLocalCallsInput.sendKeys(homeNpaLocalCalls);
  }

  async getHomeNpaLocalCallsInput() {
    return this.homeNpaLocalCallsInput.getAttribute('value');
  }

  async setHomeNpaTollCallsInput(homeNpaTollCalls) {
    await this.homeNpaTollCallsInput.sendKeys(homeNpaTollCalls);
  }

  async getHomeNpaTollCallsInput() {
    return this.homeNpaTollCallsInput.getAttribute('value');
  }

  async setForeignNpaLocalCallsInput(foreignNpaLocalCalls) {
    await this.foreignNpaLocalCallsInput.sendKeys(foreignNpaLocalCalls);
  }

  async getForeignNpaLocalCallsInput() {
    return this.foreignNpaLocalCallsInput.getAttribute('value');
  }

  async setForeignNpaTollCallsInput(foreignNpaTollCalls) {
    await this.foreignNpaTollCallsInput.sendKeys(foreignNpaTollCalls);
  }

  async getForeignNpaTollCallsInput() {
    return this.foreignNpaTollCallsInput.getAttribute('value');
  }

  async setPermHnpaLocalCallsInput(permHnpaLocalCalls) {
    await this.permHnpaLocalCallsInput.sendKeys(permHnpaLocalCalls);
  }

  async getPermHnpaLocalCallsInput() {
    return this.permHnpaLocalCallsInput.getAttribute('value');
  }

  async setPermHnpaTollCallsInput(permHnpaTollCalls) {
    await this.permHnpaTollCallsInput.sendKeys(permHnpaTollCalls);
  }

  async getPermHnpaTollCallsInput() {
    return this.permHnpaTollCallsInput.getAttribute('value');
  }

  async setPermHnpaForeignLocalCallsInput(permHnpaForeignLocalCalls) {
    await this.permHnpaForeignLocalCallsInput.sendKeys(permHnpaForeignLocalCalls);
  }

  async getPermHnpaForeignLocalCallsInput() {
    return this.permHnpaForeignLocalCallsInput.getAttribute('value');
  }

  async setDialingPlanNotesInput(dialingPlanNotes) {
    await this.dialingPlanNotesInput.sendKeys(dialingPlanNotes);
  }

  async getDialingPlanNotesInput() {
    return this.dialingPlanNotesInput.getAttribute('value');
  }

  async save() {
    await this.saveButton.click();
  }

  async cancel() {
    await this.cancelButton.click();
  }

  getSaveButton() {
    return this.saveButton;
  }

  async enterData() {
    await waitUntilDisplayed(this.saveButton);
    await this.setNpaIdInput('5');
    expect(await this.getNpaIdInput()).to.eq('5');
    await waitUntilDisplayed(this.saveButton);
    await this.setTypeOfCodeInput('typeOfCode');
    expect(await this.getTypeOfCodeInput()).to.match(/typeOfCode/);
    await waitUntilDisplayed(this.saveButton);
    await this.setAssignableInput('assignable');
    expect(await this.getAssignableInput()).to.match(/assignable/);
    await waitUntilDisplayed(this.saveButton);
    await this.setExplanationInput('explanation');
    expect(await this.getExplanationInput()).to.match(/explanation/);
    await waitUntilDisplayed(this.saveButton);
    await this.setReservedInput('reserved');
    expect(await this.getReservedInput()).to.match(/reserved/);
    await waitUntilDisplayed(this.saveButton);
    await this.setAssignedInput('assigned');
    expect(await this.getAssignedInput()).to.match(/assigned/);
    await waitUntilDisplayed(this.saveButton);
    await this.setAssignmentDateInput('01-01-2001');
    expect(await this.getAssignmentDateInput()).to.eq('2001-01-01');
    await waitUntilDisplayed(this.saveButton);
    await this.setUseTypeInput('useType');
    expect(await this.getUseTypeInput()).to.match(/useType/);
    await waitUntilDisplayed(this.saveButton);
    await this.setLocationInput('location');
    expect(await this.getLocationInput()).to.match(/location/);
    await waitUntilDisplayed(this.saveButton);
    await this.setCountryInput('country');
    expect(await this.getCountryInput()).to.match(/country/);
    await waitUntilDisplayed(this.saveButton);
    await this.setInServiceInput('inService');
    expect(await this.getInServiceInput()).to.match(/inService/);
    await waitUntilDisplayed(this.saveButton);
    await this.setInServiceDateInput('01-01-2001');
    expect(await this.getInServiceDateInput()).to.eq('2001-01-01');
    await waitUntilDisplayed(this.saveButton);
    await this.setStatusInput('status');
    expect(await this.getStatusInput()).to.match(/status/);
    await waitUntilDisplayed(this.saveButton);
    await this.setPlanningLettersInput('planningLetters');
    expect(await this.getPlanningLettersInput()).to.match(/planningLetters/);
    await waitUntilDisplayed(this.saveButton);
    await this.setNotesInput('notes');
    expect(await this.getNotesInput()).to.match(/notes/);
    await waitUntilDisplayed(this.saveButton);
    await this.setOverlayInput('overlay');
    expect(await this.getOverlayInput()).to.match(/overlay/);
    await waitUntilDisplayed(this.saveButton);
    await this.setOverlayComplexInput('overlayComplex');
    expect(await this.getOverlayComplexInput()).to.match(/overlayComplex/);
    await waitUntilDisplayed(this.saveButton);
    await this.setParentNpaIdInput('5');
    expect(await this.getParentNpaIdInput()).to.eq('5');
    await waitUntilDisplayed(this.saveButton);
    await this.setServiceInput('service');
    expect(await this.getServiceInput()).to.match(/service/);
    await waitUntilDisplayed(this.saveButton);
    await this.setTimeZoneInput('timeZone');
    expect(await this.getTimeZoneInput()).to.match(/timeZone/);
    await waitUntilDisplayed(this.saveButton);
    await this.setAreaServedInput('areaServed');
    expect(await this.getAreaServedInput()).to.match(/areaServed/);
    await waitUntilDisplayed(this.saveButton);
    await this.setMapInput('map');
    expect(await this.getMapInput()).to.match(/map/);
    await waitUntilDisplayed(this.saveButton);
    await this.setInJeopardyInput('inJeopardy');
    expect(await this.getInJeopardyInput()).to.match(/inJeopardy/);
    await waitUntilDisplayed(this.saveButton);
    await this.setReliefPlanningInProgressInput('reliefPlanningInProgress');
    expect(await this.getReliefPlanningInProgressInput()).to.match(/reliefPlanningInProgress/);
    await waitUntilDisplayed(this.saveButton);
    await this.setHomeNpaLocalCallsInput('homeNpaLocalCalls');
    expect(await this.getHomeNpaLocalCallsInput()).to.match(/homeNpaLocalCalls/);
    await waitUntilDisplayed(this.saveButton);
    await this.setHomeNpaTollCallsInput('homeNpaTollCalls');
    expect(await this.getHomeNpaTollCallsInput()).to.match(/homeNpaTollCalls/);
    await waitUntilDisplayed(this.saveButton);
    await this.setForeignNpaLocalCallsInput('foreignNpaLocalCalls');
    expect(await this.getForeignNpaLocalCallsInput()).to.match(/foreignNpaLocalCalls/);
    await waitUntilDisplayed(this.saveButton);
    await this.setForeignNpaTollCallsInput('foreignNpaTollCalls');
    expect(await this.getForeignNpaTollCallsInput()).to.match(/foreignNpaTollCalls/);
    await waitUntilDisplayed(this.saveButton);
    await this.setPermHnpaLocalCallsInput('permHnpaLocalCalls');
    expect(await this.getPermHnpaLocalCallsInput()).to.match(/permHnpaLocalCalls/);
    await waitUntilDisplayed(this.saveButton);
    await this.setPermHnpaTollCallsInput('permHnpaTollCalls');
    expect(await this.getPermHnpaTollCallsInput()).to.match(/permHnpaTollCalls/);
    await waitUntilDisplayed(this.saveButton);
    await this.setPermHnpaForeignLocalCallsInput('permHnpaForeignLocalCalls');
    expect(await this.getPermHnpaForeignLocalCallsInput()).to.match(/permHnpaForeignLocalCalls/);
    await waitUntilDisplayed(this.saveButton);
    await this.setDialingPlanNotesInput('dialingPlanNotes');
    expect(await this.getDialingPlanNotesInput()).to.match(/dialingPlanNotes/);
    await this.save();
    await waitUntilHidden(this.saveButton);
    expect(await isVisible(this.saveButton)).to.be.false;
  }
}
