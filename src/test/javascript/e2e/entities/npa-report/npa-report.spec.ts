import { browser, element, by } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import SignInPage from './../../page-objects/signin-page';
import NpaReportComponentsPage from './npa-report.page-object';
import NpaReportUpdatePage from './npa-report-update.page-object';
import {
  waitUntilDisplayed,
  waitUntilAnyDisplayed,
  click,
  getRecordsCount,
  waitUntilHidden,
  waitUntilCount,
  isVisible,
} from '../../util/utils';

const expect = chai.expect;

describe('NpaReport e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let npaReportComponentsPage: NpaReportComponentsPage;
  let npaReportUpdatePage: NpaReportUpdatePage;
  const username = process.env.E2E_USERNAME ?? 'admin';
  const password = process.env.E2E_PASSWORD ?? 'admin';

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.waitUntilDisplayed();
    await signInPage.username.sendKeys(username);
    await signInPage.password.sendKeys(password);
    await signInPage.loginButton.click();
    await signInPage.waitUntilHidden();
    await waitUntilDisplayed(navBarPage.entityMenu);
    await waitUntilDisplayed(navBarPage.adminMenu);
    await waitUntilDisplayed(navBarPage.accountMenu);
  });

  beforeEach(async () => {
    await browser.get('/');
    await waitUntilDisplayed(navBarPage.entityMenu);
    npaReportComponentsPage = new NpaReportComponentsPage();
    npaReportComponentsPage = await npaReportComponentsPage.goToPage(navBarPage);
  });

  it('should load NpaReports', async () => {
    expect(await npaReportComponentsPage.title.getText()).to.match(/Npa Reports/);
    expect(await npaReportComponentsPage.createButton.isEnabled()).to.be.true;
  });

  it('should create and delete NpaReports', async () => {
    const beforeRecordsCount = (await isVisible(npaReportComponentsPage.noRecords))
      ? 0
      : await getRecordsCount(npaReportComponentsPage.table);
    npaReportUpdatePage = await npaReportComponentsPage.goToCreateNpaReport();
    await npaReportUpdatePage.enterData();

    expect(await npaReportComponentsPage.createButton.isEnabled()).to.be.true;
    await waitUntilDisplayed(npaReportComponentsPage.table);
    await waitUntilCount(npaReportComponentsPage.records, beforeRecordsCount + 1);
    expect(await npaReportComponentsPage.records.count()).to.eq(beforeRecordsCount + 1);

    await npaReportComponentsPage.deleteNpaReport();
    if (beforeRecordsCount !== 0) {
      await waitUntilCount(npaReportComponentsPage.records, beforeRecordsCount);
      expect(await npaReportComponentsPage.records.count()).to.eq(beforeRecordsCount);
    } else {
      await waitUntilDisplayed(npaReportComponentsPage.noRecords);
    }
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
