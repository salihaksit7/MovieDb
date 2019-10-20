# MovieDb

`platform info`
* Android Studio 3.5.1
* Api 21- 29
* Kotlin

`branches`
* master
* dev
  
`how to add new cluster`
 - just add new enum as MovieListType 
   - KEY([title], [sort_type], [proper_order]),
     ```kotlin
     NonPopular("Non Popular", "popularity.asc", 5),
     ```
