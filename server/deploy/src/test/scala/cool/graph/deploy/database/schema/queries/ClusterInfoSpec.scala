package cool.graph.deploy.database.schema.queries

import cool.graph.deploy.specutils.DeploySpecBase
import cool.graph.shared.models.ProjectId
import org.scalatest.{FlatSpec, Matchers}

class ClusterInfoSpec extends FlatSpec with Matchers with DeploySpecBase {

  "ClusterInfo query" should "return cluster version" in {
    val (project, _) = setupProject(basicTypesGql)
    val nameAndStage = ProjectId.fromEncodedString(project.id)
    val result       = server.query(s"""
                                       |query {
                                       |  clusterInfo {
                                       |    version
                                       |  }
                                       |}
      """.stripMargin)

    result.pathAsString("data.clusterInfo.version") shouldEqual sys.env("CLUSTER_VERSION")
  }
}
