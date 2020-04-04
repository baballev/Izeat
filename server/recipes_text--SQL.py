## Créer un requte SQL

ingredients=input("Donner les ingrédients: "+'\n')
baking_time=input("Donner le temps de cuisson: "+'\n')
meal=input("Donner le nom du plat: "+'\n')
proteins_g=int(input("Donner la valeur nutritive en gramme: "+'\n'))
lipids_g=int(input("Donner la valeur nutritive en gramme: "+'\n'))
carbohydrates_g=int(input("Donner la valeur nutritive en gramme: "+'\n'))
calories_Kcal=int(input("Donner la valeur calorique en Kcal: "+'\n'))
preparation=input("Donner la preparation :"+'\n')
prep_time=input("Donner le temps de préparation: "+'\n')
print( 'INSERT INTO recipes(ingredients,baking_time,meal,proteins_g,lipids_g,carbohydrates_g,calories_Kcal,preparation,prep_time) VALUES("'+ingredients+'","'+baking_time+'","'+meal+'",'+str(proteins_g)+','+str(lipids_g)+','+str(carbohydrates_g)+','+str(calories_Kcal)+',"'+preparation+'","'+prep_time+'");')
