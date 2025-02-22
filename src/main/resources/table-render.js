
fetch('http://localhost:8080/api/data/pdf')
    .then(response => response.json())
    .then(data => {
        console.log("reached here inside script");
        console.log(data);

        // Process the data for the chart and table
        const labels = data.map(item => item.Year);
        const values = data.map(item => item.Memberships_Sold);

        console.log("labels");
        console.log(labels);
        console.log("values");
        console.log(values);

        console.log("done");
        // Update the bar chart
        const ctx = document.getElementById('myBarChart').getContext('2d');
        new Chart(ctx, {
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

        // Update the table
        const tableBody = document.getElementById('dataTable').getElementsByTagName('tbody')[0];
        data.forEach(item => {
            const row = tableBody.insertRow();
            const cell1
