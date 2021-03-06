package com.avito.report

import com.avito.report.model.AndroidTest
import com.avito.report.model.ReportCoordinates
import com.avito.report.model.TestStaticDataPackage
import com.avito.report.model.createStubInstance
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockReportsExtension::class)
internal class GroupingKeyTest {

    @Test
    fun `grouping_key sent for dataSet without testCaseId`(reports: MockReportApi) {
        reports.addTest(
            reportCoordinates = ReportCoordinates.createStubInstance(),
            buildId = "1234",
            test = AndroidTest.Completed.createStubInstance(
                testStaticData = TestStaticDataPackage.createStubInstance(
                    testCaseId = null,
                    dataSetNumber = 1,
                    name = "com.avito.Test.test"
                )
            )
        )
            .singleRequestCaptured()
            .bodyContains("\"grouping_key\":\"com.avito.Test\"")
    }

    @Test
    fun `grouping_key doesnt sent for dataSet with testCaseId`(reports: MockReportApi) {
        reports.addTest(
            reportCoordinates = ReportCoordinates.createStubInstance(),
            buildId = "1234",
            test = AndroidTest.Completed.createStubInstance(
                testStaticData = TestStaticDataPackage.createStubInstance(
                    testCaseId = 12345,
                    dataSetNumber = 1,
                    name = "com.avito.Test.test"
                )
            )
        )
            .singleRequestCaptured()
            .bodyDoesntContain("grouping_key")
    }

    @Test
    fun `grouping_key doesnt sent for testCase without dataset`(reports: MockReportApi) {
        reports.addTest(
            reportCoordinates = ReportCoordinates.createStubInstance(),
            buildId = "1234",
            test = AndroidTest.Completed.createStubInstance(
                testStaticData = TestStaticDataPackage.createStubInstance(
                    testCaseId = 12345,
                    dataSetNumber = null,
                    name = "com.avito.Test.test"
                )
            )
        )
            .singleRequestCaptured()
            .bodyDoesntContain("grouping_key")
    }
}
