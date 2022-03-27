# execute-regex
This project demonstrates data extraction from text through regular expressions.

APIs:

1. Execute Regex on Text.
   * Type: POST
   * URL: http://localhost:8080/api/v1/regex
   * Request/Response Headers:
     * Content-Type: application/json
   * Request Body: {"regex": "<regex>", "textBody": "<textBody>"}
   * Response:
     * Status: If match found: 200 OK:
       * {"match": "<match_text>", "error": false}
     * Status: If match NOT found: 200 OK
       * {"match": null, "error": true}
     * Status: If TIMEOUT: 200 OK:
       * {"match": null, "error": true}
     * Status: BAD REQUEST: 400
       * {"errors": ["The field: '<field_name>' is required."]}
     * Status: Internal Server Error: 500
       * {"match": null, "error": true}
