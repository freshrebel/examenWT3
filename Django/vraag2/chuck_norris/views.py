from django.shortcuts import render
from django.http import HttpResponse

def index(request):
    output = "<form method='POST' action='joke/'>"
    output += "voornaam: <input type='text' name='voornaam' /><br>"
    output += "achternaam: <input type='text' name='achternaam' /><br>"
    output += "<button type='submit' name='submit'>submit</button>"
    output += "</form>"
    return HttpResponse(output)

def joke(request):
    #ik kreeg deze error
    #http://stackoverflow.com/questions/9692625/csrf-verification-failed-request-aborted-on-django
    firstName = "John" #request.POST['voornaam']
    lastName = "Doe" #request.POST['achternaam']
    url = 'http://api.icndb.com/jokes/random?firstName=' + firstName + '&lastName=' + lastName
    params = {'firstName': firstName, 'lastName': lastName}
    r= request.get(url, params=params)
    return HttpResponse(r)
