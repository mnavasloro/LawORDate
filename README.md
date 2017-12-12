# LawORDate

Software for detecting legal references that could be mistaken to dates by temporal taggers. Works on Spanish texts for now.

A POST request containing the text to parse as the body should be sent to http://pgn2rdf.appspot.com/lawordate

The current return is as follows:

TEXT_WITHOUT_LEGAL_REFERENCES
---------------------
MAP_OR_REPLACEMENTS
Replacement\t---->\tOriginalLegalReference\n
---------------------
TEXT_WITH_LEGAL_REFERENCES