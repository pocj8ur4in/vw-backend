document.addEventListener("DOMContentLoaded", function() {
    const yearSelect = document.getElementById("year");
    const monthSelect = document.getElementById("month");
    const daySelect = document.getElementById("day");

    yearSelect.addEventListener("change", function () {
        const selectedYear = yearSelect.value;
        const selectedMonth = monthSelect.value;

        if (selectedYear !== "0") {
            if (selectedMonth !== "0") {
                daySelect.innerHTML = '';
                const option = document.createElement("option");
                option.value = "";
                option.text = "일...";
                daySelect.appendChild(option);

                const lastDay = new Date(selectedYear, selectedMonth, 0).getDate();

                for (let i = 1; i <= lastDay; i++) {
                    const option = document.createElement("option");
                    option.value = i.toString();
                    option.text = i.toString() + "일";
                    daySelect.appendChild(option);
                }
                daySelect.style.display = "block";
            }

            monthSelect.style.display = "block";
        } else {
            monthSelect.style.display = "none";
            monthSelect.value = "";
            daySelect.style.display = "none";
            daySelect.value = "";
        }
    });

    monthSelect.addEventListener("change", function () {
        const selectedYear = yearSelect.value;
        const selectedMonth = monthSelect.value;

        if (selectedMonth !== "0") {
            daySelect.innerHTML = '';
            const option = document.createElement("option");
            option.value = "0";
            option.text = "일...";
            daySelect.appendChild(option);


            const lastDay = new Date(selectedYear, selectedMonth, 0).getDate();

            for (let i = 1; i <= lastDay; i++) {
                const option = document.createElement("option");
                option.value = i.toString();
                option.text = i.toString() + "일";
                daySelect.appendChild(option);
            }

            daySelect.style.display = "block";

        } else {
            daySelect.style.display = "none";
            daySelect.value = "";
        }
    });

    songSearch();
});

function toggleActive(element) {
    var buttons = document.querySelectorAll('.btn.btn-outline-secondary');

    var allButtonsActive = true;
    buttons.forEach(function (button) {
        if (!button.classList.contains("active")) {
            allButtonsActive = false;
        }
    });

    if (allButtonsActive) {
        buttons.forEach(function (button) {
            if (button !== element) {
                button.classList.remove("active");
            }
        });
    } else {
        element.classList.toggle("active");
    }

    songSearch();
}

function toogleDetails(element) {
    const yearSelect = document.getElementById("details");
    element.classList.toggle("active");

    if (yearSelect.style.display === "none") {
        yearSelect.style.display = "block";
    } else {
        yearSelect.style.display = "none";
    }
}

function songSearch() {
    const nameElement = document.getElementById("name");
    const name = nameElement ? nameElement.value : "";

    const typeElements = document.querySelectorAll('.btn.btn-outline-secondary.active');
    const typeMappings = {
        'COVER': '커버',
        'MASHUP': '매쉬업',
        'MUSIC_PV': '뮤직 비디오',
        'ORIGINAL': '오리지널',
        'REMASTER': '리마스터',
        'REMIX': '리믹스'
    };

    const type = Array.from(typeElements)
        .map(element => element.textContent.trim())
        .filter(selectedType => Object.keys(typeMappings).includes(selectedType))
        .map(selectedType => typeMappings[selectedType]);

    const typeString = type.join(', ');

    const artistElement = document.getElementById("song_artist");
    const artist = artistElement ? artistElement.value : "";

    const vocalistElement = document.getElementById("song_vocalist");
    const vocalist = vocalistElement ? vocalistElement.value : "";

    const parentElement = document.getElementById("parent");
    const parent = parentElement ? parentElement.value : "";

    const sort = document.getElementById("sort").value;
    const num = document.getElementById("number").value;

    const yearElement = document.getElementById("year");
    const year = yearElement ? (yearElement.value !== "0" ? yearElement.value : "") : "";

    const monthElement = document.getElementById("month");
    const month = monthElement ? (monthElement.value !== "0" ? monthElement.value : "") : "";

    const dayElement = document.getElementById("day");
    const day = dayElement ? (dayElement.value !== "0" ? dayElement.value : "") : "";

    $.ajax({
        url: "/v1/search/song",
        type: 'POST',
        async: false,
        data: {
            name: name,
            type: typeString,
            artist: artist,
            vocalist: vocalist,
            parent: parent,
            sort: sort,
            num: num,
            year: year,
            month: month,
            day: day
        },
        success: function (data) {
        },
        error: function (error) {
            alert(error.responseText);
        }
    });
}
