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



fetch('http://localhost:8080/api/csv')
    .then(response => response.json())
    .then(data => {

        // Extract labels and values
        const labels = data.map(item => item.Date);  // Change based on actual JSON keys
        const values = data.map(item => item.Duration);  // Change based on actual JSON keys

        // Update the line chart
        const ctx = document.getElementById('myLineChart');
        if (ctx) {
            new Chart(ctx.getContext('2d'), {
                type: 'line',  // Changed from 'bar' to 'line' to match variable name
                data: {
                    labels: labels,
                    datasets: [{
                        label: 'Duration',
                        data: values,
                        borderColor: 'rgba(0, 123, 255, 1)',  // Line color
                        backgroundColor: 'rgba(0, 123, 255, 0.2)',  // Fill color
                        fill: true,
                        tension: 0.1  // Smooth out the line
                    }]
                }
            });
        } else {
            console.error("Canvas element with id 'myLineChart' not found.");
        }
    })
    .catch(error => {
        console.error("Error fetching the data:", error);
    });
