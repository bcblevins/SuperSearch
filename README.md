# SuperSearch  

This program is a versatile text search tool capable of searching for keywords using plain text or patterns using Regular Expressions. It supports searching from both files and the clipboard. 

## Current Capabilities
- Plain text search
  - Search file or clipboard for a keyword
  - Return line and line number of occurrences 
  

- Pattern-based search
  - Search file or clipboard for a pattern, returning all instances of the pattern
  - Choose from a categorized and sub-categorized collection of custom made regular expressions
  

- Pattern maker
  - Using a custom made simplified language, aids the user in making their own pattern.
  - This pattern can be used in pattern-based search.


## Potential Future Capabilities
- Currently, the pattern search only returns matches for the pattern. I'd like it optionally to return the pattern matches with context, like the rest of the line or a certain number of characters surrounding it.
- User patterns to optionally be added to the collection of available patterns for future users.
- Add AND and OR search capability for multiple patterns