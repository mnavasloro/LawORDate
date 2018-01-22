# LawORDate

Software for detecting legal references that could be mistaken to dates by temporal taggers. Works on Spanish texts for now.

A POST request containing the text to parse as the body should be sent to http://legalwhen.appspot.com/lawordate

The current return (as text for better readability) is as follows:

```
(TEXT_WITHOUT_LEGAL_REFERENCES)
---------------------\n
(MAP_OR_REPLACEMENTS with the following format for each one:)
Replacement\t---->\tOriginalLegalReference\n
---------------------\n
(TEXT_WITH_LEGAL_REFERENCES)
```

Next implementation will switch to JSON format.

See more here: http://ceur-ws.org/Vol-2049/04paper.pdf


