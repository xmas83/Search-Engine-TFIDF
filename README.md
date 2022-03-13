Search engine in Java.
 
The search engine has been implemented as an inverted index (https://en.wikipedia.org/wiki/Inverted_index)
that runs in memory and can return a result list that is sorted by TF-IDF
(https://en.wikipedia.org/wiki/Tf*idf).

The search engine:
• is able to take in a list of documents
• supports searches for single terms in the document set (https://en.wikipedia.org/wiki/Tokenization)
• returns a list of matching documents sorted by TF-IDF.

- TF (using Wikipedia terminology):
term frequency adjusted for document length: ft,d ÷ (number of words in d)
augmented frequency

- IDF (using Wikipedia terminology):

Example

The following documents are indexed:
Document 1: “the brown fox jumped over the brown dog”
Document 2: “the lazy brown dog sat in the corner”
Document 3: “the red fox bit the lazy dog”

A search for “brown” should now return the list: [document 1, document 2].
A search for “fox” should return the list: [document 3, document 1]


Documents can contain a simple string or multi strings. 