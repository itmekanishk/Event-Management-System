document.addEventListener("DOMContentLoaded", function() {
    fetchEvents();

    async function fetchEvents() {
        const response = await fetch('EventServlet?action=list');
        const events = await response.json();

        const eventList = document.getElementById("events");
        events.forEach(event => {
            const li = document.createElement('li');
            li.textContent = `${event.event_name} - ${event.event_date} at ${event.venue}`;
            eventList.appendChild(li);
        });
    }
});
