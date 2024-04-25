<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>JSP - Hello World</title>
    <script>
        // Fonction pour tester Weather API
        function submitForm(e) {
            e.preventDefault();
            var city = document.getElementById('cityName').value;
            var xhr = new XMLHttpRequest();
            xhr.open('GET', 'weather-api?city=' + encodeURIComponent(city), true);
            xhr.onload = function () {
                if (xhr.status >= 200 && xhr.status < 300) {
                    document.getElementById('weatherResult').textContent = xhr.responseText;
                } else {
                    document.getElementById('weatherResult').textContent = 'Erreur lors de la récupération des informations.';
                }
            };
            xhr.send();
        }

        // Fonction pour tester IP-API
        function fetchCity() {
            var ip = document.getElementById('ipAddress').value;
            var xhr = new XMLHttpRequest();
            xhr.onreadystatechange = function() {
                if (xhr.readyState === 4 && xhr.status === 200) {
                    document.getElementById('cityResult').textContent = 'Informations : ' + xhr.responseText;
                } else if (xhr.readyState === 4) {
                    document.getElementById('cityResult').textContent = 'Erreur lors de la récupération des informations pour l\'IP ' + ip;
                }
            };
            xhr.open('GET', 'ipapi?ip=' + encodeURIComponent(ip), true);
            xhr.send();
        }

        // Fonction pour interroger le servlet de conversion de devise
        function convertCurrency() {
            var fromCurrency = document.getElementById('fromCurrency').value;
            var toCurrency = document.getElementById('toCurrency').value;
            var amount = document.getElementById('amount').value;

            var xhr = new XMLHttpRequest();
            xhr.onreadystatechange = function() {
                if (xhr.readyState === 4 && xhr.status === 200) {
                    document.getElementById('conversionResult').textContent = 'Résultat de la conversion : ' + xhr.responseText;
                } else if (xhr.readyState === 4) {
                    document.getElementById('conversionResult').textContent = 'Erreur lors de la conversion.';
                }
            };
            xhr.open('GET', 'convertCurrency?from=' + encodeURIComponent(fromCurrency) +
                '&to=' + encodeURIComponent(toCurrency) +
                '&amount=' + encodeURIComponent(amount), true);
            xhr.send();
        }
    </script>
</head>
<body>
<h1>Hello World!</h1>
<br/>
<a href="hello-servlet">Hello Servlet</a>
<br/><br/>

<!-- Formulaire pour tester Weather API -->
<h2>Test Weather API</h2>
<form id="weatherForm" onsubmit="submitForm(event);">
    <label for="cityName">Nom de la ville :</label>
    <input type="text" id="cityName" name="cityName" required>
    <button type="submit">Vérifier la météo</button>
</form>
<div id="weatherResult"></div>
<br/>

<!-- Formulaire pour tester IP-API -->
<h2>Test IP-API</h2>
<label for="ipAddress">Entrez une adresse IP :</label>
<input type="text" id="ipAddress" name="ipAddress" required placeholder="@IP de Machine du Client " >
<button onclick="fetchCity()">Localisation</button>
<div id="cityResult"></div>
<br/>

<!-- Formulaire pour interroger le servlet de conversion de devise -->
<h2>Conversion de Devise</h2>
<form onsubmit="convertCurrency(); return false;">
    <label for="fromCurrency">De :</label>
    <input type="text" id="fromCurrency" name="fromCurrency" required placeholder="USD">

    <label for="toCurrency">À :</label>
    <input type="text" id="toCurrency" name="toCurrency" required placeholder="EUR">

    <label for="amount">Montant :</label>
    <input type="number" id="amount" name="amount" required placeholder="100">

    <button type="submit">Convertir</button>
</form>
<div id="conversionResult"></div>
</body>
</html>
