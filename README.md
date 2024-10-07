
## Features

- **User Registration**: Users can register with their email address.
- **Movie Search**: Users can search for movies by title, cast, or category.
- **View Movie Details**: Users can see details for each movie, including title, cast, category, release date, and budget.
- **Favorites Management**: Users can add or remove movies from their favorites list.
- **Personal Details**: Users can view their registered email and favorite movies.

**Clone the repository**:
   git clone https://github.com/Foysal1327/movie-list-app.git

   Build the application:
   mvn clean install

   Run the application: ./mvnw spring-boot:run

   Open your browser and go to http://localhost:8080

  **API Endpoints**

**Movies**

- Get all movies

GET /movies

Description: Retrieve all available movies.

Response: A list of all movies.
- Search for movies

GET /movies/search?q={query}

Description: Search for movies by title, cast, or category. The results are sorted in ascending order by title.

Parameters:
q (String): The query for searching movies by title, cast, or category.
Response: A list of movies matching the search criteria.
- Get movie details by title

GET /movies/details?title={title}

Description: Retrieve the details of a specific movie by its title.

Parameters:
title (String): The title of the movie.

Response:
200 OK: Returns the movie details.
404 Not Found: If the movie is not found.
Favorites
- Add a movie to favorites

POST /movies/favorites/add

Description: Add a movie to the logged-in user's favorites list.
Parameters:
title (String): The title of the movie to add to favorites.

Response:
200 OK: A success message indicating that the movie was added to favorites.
404 Not Found: If the movie is not found in the list.
- Remove a movie from favorites

POST /movies/favorites/remove

Description: Remove a movie from the user's favorites list.
Parameters:
title (String): The title of the movie to remove from favorites.

Response:
200 OK: A success message indicating that the movie was removed from favorites.
404 Not Found: If the movie is not found in the user's favorites list.
- View favorite movies

GET /movies/favorites

Description: View the list of the user's favorite movies.

Response: A list of movies in the user's favorites

**Users**

- Register a new user

POST /users/register

Description: Registers a new user with an email address.

Parameters:
email (String): The email address of the user to register.

Response:
200 OK: A success message indicating that the user was registered.
400 Bad Request: If the user is already registered.

- Get all registered users

GET /users/all

Description: Retrieve a list of all registered users.

Response: A map containing registered users' emails and their details.
    
