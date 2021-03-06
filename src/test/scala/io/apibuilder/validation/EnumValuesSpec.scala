package io.apibuilder.validation

import org.scalatest.{FunSpec, Matchers}
import play.api.libs.json.{JsObject, Json}

class EnumValuesSpec extends FunSpec with Matchers with helpers.Helpers {

  def makeFraudReview(riskEvaluation: String = "Pending"): JsObject = {
    Json.obj(
      "description" -> "lorem ipsum tmwHAn",
      "attributes" -> Json.obj(),
      "status_updated_at" -> "2019-11-04T20:22:31.861Z",
      "id" -> "lorem ipsum sRVdId",
      "order" -> Json.obj(
        "id" -> "lorem ipsum KllOf0",
        "number" -> "lorem ipsum KTjUpR"
      ),
      "status" -> "pending",
      "responsible_party" -> "flow",
      "liability" -> "flow",
      "organization_id" -> "lorem ipsum XlUr8X",
      "risk_evaluation" -> riskEvaluation
    )
  }

  it("serialize enums with values") {
    val fraudReview = flowMultiService.findType(
      "io.flow.internal.v0", "fraud_review"
    ).get

    def assertValid(value: String) = {
      rightOrErrors {
        flowMultiService.upcast(fraudReview, makeFraudReview(value))
      }
    }
    assertValid("Low-Risk") // use enum_value.value
    assertValid("low")      // use enum_value.name
  }

}
