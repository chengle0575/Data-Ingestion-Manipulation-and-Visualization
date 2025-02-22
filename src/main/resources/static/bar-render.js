fetch('http://localhost:8080/api/data/pdf')
    .then(response => response.json())
    .then(data => {
        // Process the data for the chart and table
        const labels = data.map(item => item.Year);
        const values = data.map(item => item.Memberships_Sold);

        // Update the bar chart
        const ctx = document.getElementById('myBarChart');
        if (ctx) {
            new Chart(ctx.getContext('2d'), {
                type: 'bar',
                data: {
                    labels: labels,
                    datasets: [{
                        label: 'Memberships Sold',
                        data: values,
                        backgroundColor: 'rgba(0, 123, 255, 0.5)',
                    }]
                }
            });
        } else {
            console.error("Canvas element with id 'myBarChart' not found.");
        }

    })
    .catch(error => {
        console.error("Error fetching data:", error);
    });


