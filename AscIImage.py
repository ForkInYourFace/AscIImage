from PIL import Image
from math import fsum
from math import floor
import sys
from os.path import isfile, isdir, dirname


M_DEFAULT="#%§Ga?!+;*^,. "
M_BLACK=" .,^*;+!?aG§%#"

def lightLevel(im):
    try:
        list_lum=[]
        for x in range(im.size[0]):
            for y in range(im.size[1]):
                list_lum.append(fsum(im.getpixel((x,y)))/3)
        return fsum(list_lum)/len(list_lum)
    except:
        return 0

def lightToAscii(light,matrice):
    return(matrice[floor((light/255)*(len(matrice)-1))])

def imageToAscii(file,outFile,matrice,xLetter,yLetter):

    try:
        im = Image.open(file)
    except FileNotFoundError:
        print("Image non trouvée")
        exit()
    except:
        print("Erreur inconnu")
        exit()

    xres=im.size[0]/xLetter
    yres=im.size[1]/yLetter

    finalSize=(xLetter,yLetter)

    text=""
    print("Traitement")
    for y in range(finalSize[1]):
        for x in range(finalSize[0]):
            light=lightLevel(im.crop((floor(x*xres),floor(y*yres),floor((x+1)*xres),floor((y+1)*yres))))
            text+=lightToAscii(light,matrice)
        text+="\n"

    try:
        with open(outFile,"wb") as f:
            f.write(text.encode("utf-8"))
    except:
        print("Erreur lors de l'écriture du fichier texte")
        exit()

    print("Done !")

if(__name__=="__main__"):
    argError=False
    if(len(sys.argv)==1):
        ask=True
        while ask:
            file=input("Chemin du fichier: ")
            if isfile(file):
                ask=False
            else:
                print("Chermin incorect")
        ask=True
        while ask:
            textFile=input("Chemin du fichier texte de sortie : ")
            if(isdir(dirname(textFile))):
                ask=False
            else:
                print("Chemin Incorrect")
        ask=True
        while ask:
            matrice=input("Entrez la matrice de lettre (Laissez blanc par défaut et précédez de // pour choisir une matrice prédéfinie): ")
            ask=False
            if(matrice==""):
                matrice=M_DEFAULT
            elif (matrice.startswith("//")):
                if(matrice=="//default"):
                    matrice=M_DEFAULT
                elif(matrice=="//black" or matrice=="//inverted"):
                    matrice=M_BLACK
                else:
                    print("Modéle prédéfinie inexistant")
                    ask=True
        ask=True
        while ask:
            letter=input("Nombre de lettre en X (Laissez vide par défaut) :")
            if(letter==""):
                nbrLetterX=500
                ask=False
            else:
                try:
                    nbrLetterX=int(letter)
                except:
                    print("Rentrez un nombre")
                else:
                    if(nbrLetterX>0):
                        ask=False
                    else:
                        print("Le nombre doit être supérieur à 0")
        ask=True
        while ask:
            letter=input("Nombre de lettre en Y (Laissez vide par défaut) :")
            if(letter==""):
                nbrLetterY=250
                ask=False
            else:
                try:
                    nbrLetterY=int(letter)
                except:
                    print("Rentrez un nombre")
                else:
                    if(nbrLetterY>0):
                        ask=False
                    else:
                        print("Le nombre doit être supérieur à 0")
    elif sys.argv[1]=="-h" or sys.argv[1]=="-help" or sys.argv[1]=="help":
        print(f"""asciImage [imagePath [textFilePath] ([-m matrice],[-x sizeX],[-y sizeY]) ]\n
            \n
            imagePath: lien vers l'image à convertir
            textFilePath: lien vers le fichier texte de sortie (le dossier doit déjà exister) (./ASCII.txt par défaut)
            matrice: caractère utilisés, du plus sombre au plus clair
                    \t\t utiliser // au début pour utiliser un modèle par défaut:
                    default: {M_DEFAULT}
                    black or inverted: {M_BLACK}
            sizeX: nombre de lettre par ligne (500 par défaut)
            sizeY: nombre de ligne (250 par défaut)

            Si asciImage est utilisé seul on menu d'aide sera ouvert
        """)
        exit()
    elif len(sys.argv)==2:
        file=sys.argv[1]
        textFile="./ASCII.txt"
        matrice=M_DEFAULT
        nbrLetterX=500
        nbrLetterY=250
    elif len(sys.argv)==3:
        file=sys.argv[1]
        textFile=sys.argv[2]
        matrice=M_DEFAULT
        nbrLetterX=500
        nbrLetterY=250
    else:
        file=sys.argv[1]
        if(isdir(dirname(sys.argv[2]))):
            textFile=sys.argv[2]
            i=3
        else:
            textFile="./ASCII.txt"
            i=2
        matrice=M_DEFAULT
        nbrLetterX=500
        nbrLetterY=250
        while i<len(sys.argv)-1:
            if(sys.argv[i]=="-m"):
                matrice=sys.argv[i+1]
                if(matrice==""):
                    matrice=M_DEFAULT
                elif (matrice.startswith("//")):
                    if(matrice=="//default"):
                        matrice=M_DEFAULT
                    elif(matrice=="//black" or matrice=="//inverted"):
                        matrice=M_BLACK
                    else:
                        print("Modéle prédéfinie inexistant")
                        argError=True
            elif(sys.argv[i]=="-x"):
                try:
                    nbrLetterX=int(sys.argv[i+1])
                except:
                    print("L'argument -x doit être un nombre")
                    argError=True
                else:
                    if(nbrLetterX<=0):
                        print("L'argument -x doit être supérieur à 0")
                        argError=True
            elif(sys.argv[i]=="-y"):
                try:
                    nbrLetterY=int(sys.argv[i+1])
                except:
                    print("L'argument -y doit être un nombre")
                    argError=True
                else:
                    if(nbrLetterY<=0):
                        print("L'argument -y doit être supérieur à 0")
                        argError=True
            else:
                argError=True
        
            if(argError):
                print("Argument non reconnu ou erroné, sortie du programme")
                exit()
            i+=2
    imageToAscii(file,textFile,matrice,nbrLetterX,nbrLetterY)