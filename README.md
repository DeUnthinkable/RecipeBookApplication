# My Recipe Book Application 
Application's start view

![Screenshot of the start view of of the application](https://github.com/DeUnthinkable/RecipeBookApplication/assets/108562056/5b8d15ae-b207-4724-93a1-c2ed716a6a92)

Application recipe view

![Screenshot of the recipe view of the application](https://github.com/DeUnthinkable/RecipeBookApplication/assets/108562056/e8e608fe-5d4b-4ecd-a04e-5fafd7f8f0c7)






## Desciption

### High-level description
This application’s function is to facilitate storing cooking recipes by providing a simple Graphical User Interface(GUI) which allows the app’s users to add, remove and update recipes and their relevent information dynamically. Each individual recipe stores its ingredients, steps, preparation time and description which ensures the users will find all the information they need in one place. 

### Detailed description
The application will have two views: a start view and a detailed-recipe view. 

The start view contains a list of recipes that the user can extend by adding more recipes and shorten by removing recipes. Opening an idividual recipe will switch the application to the detailed-recipe view and will load the view with the information of the selected recipe.

The detailed-recipe view allows the users to go back to start view with a “back” button. It contains the name of a recipe as a header title, a description as a text box, an ingredients list, a preperation steps list and the preperation time. All the lists can be edited. 

The application stores information temporarily inside a data structure, which is then deleted after the app is closed. It also saves them inside a Comma Seperated Values(CSV) file in which they remain saved, even after the app is closed, unless deleted from inside the application or directly through the CSV file.

## Langauges Used
Java: Main programming language

FXML: Layout, structuring, and styling

## How to Install and Run the Project
1. Download the latest Java JDK.
2. Download the jar file from the latest realse notes
3. Run the jar file
