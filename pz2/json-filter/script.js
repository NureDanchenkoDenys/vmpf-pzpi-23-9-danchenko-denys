function filterProducts() {

    fetch('products.json')
        .then(response => response.text())
        .then(data => {

            const products = JSON.parse(data);

            const filterValue =
                document.getElementById('filterInput')
                .value
                .toLowerCase();

            const filteredProducts = products.filter(product =>
                product.category.toLowerCase() === filterValue
            );

            const resultList =
                document.getElementById('resultList');

            resultList.innerHTML = '';

            filteredProducts.forEach(product => {

                const li = document.createElement('li');

                li.textContent =
                    `${product.name} - ${product.category}`;

                resultList.appendChild(li);
            });

            if (filteredProducts.length === 0) {

                const li = document.createElement('li');

                li.textContent = 'Нічого не знайдено';

                resultList.appendChild(li);
            }

        })
        .catch(error => {
            console.log('Помилка:', error);
        });
}