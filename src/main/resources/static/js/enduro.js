const URLS = {
    enduro: '/api/enduro',
};

const toString = ({name, frame, fork, tires, brakes}) => {
    let columns = `
        <td>${name}</td>
        <td>${frame}</td>
        <td>${fork}</td>
        <td>${tires}</td>
        <td>${brakes}</td>`;

    return `<tr>${columns}</tr>`
};

fetch(URLS.enduro)
    .then(response => response.json())
    .then(enduro => {
        let result = '';
        enduro.forEach(e => {
            const enduroString = toString(e);
            result += enduroString;
        });
        $('#enduro-table').html(result);
    });