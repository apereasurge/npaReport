package om.nanpa.cnas.common.repository;

import om.nanpa.cnas.common.domain.NpaReport;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the NpaReport entity.
 */
@SuppressWarnings("unused")
@Repository
public interface NpaReportRepository extends JpaRepository<NpaReport, Long>, JpaSpecificationExecutor<NpaReport> {}
