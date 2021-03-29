package om.nanpa.cnas.common.domain;

import static org.assertj.core.api.Assertions.assertThat;

import om.nanpa.cnas.common.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class NpaReportTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(NpaReport.class);
        NpaReport npaReport1 = new NpaReport();
        npaReport1.setId(1L);
        NpaReport npaReport2 = new NpaReport();
        npaReport2.setId(npaReport1.getId());
        assertThat(npaReport1).isEqualTo(npaReport2);
        npaReport2.setId(2L);
        assertThat(npaReport1).isNotEqualTo(npaReport2);
        npaReport1.setId(null);
        assertThat(npaReport1).isNotEqualTo(npaReport2);
    }
}
