    function onScanSuccess(decodedText, decodedResult) {
        // La URL del QR probablemente sea algo como "http://[server]/cafe:TOKEN"
        // Extraemos solo el token después del último dos puntos
        const parts = decodedText.split(':');
        const token = parts[parts.length - 1];

        document.getElementById('result').innerHTML = '<h3>Procesando compra...</h3>';

        // Llamada al endpoint del backend para usar el token
        fetch('/qr/usar', {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify({token: token})
        })
        .then(response => response.text())
        .then(data => {
            // Mostrar la respuesta del servidor (éxito o error de compra)
            document.getElementById('result').innerHTML = '<h3>Resultado:</h3><p>' + data + '</p>';
        })
        .catch(error => {
            document.getElementById('result').innerHTML = '<h3>Error de Conexión</h3><p>No se pudo conectar con el servidor.</p>';
            console.error('Error al procesar QR:', error);
        });
    }

    let config = { fps: 10, qrbox: { width: 250, height: 250 } };

    let html5QrcodeScanner = new Html5QrcodeScanner("qr-reader", config, false);

    html5QrcodeScanner.render(onScanSuccess);