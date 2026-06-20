# 🚲 Holeshot

> *Built by a rider who has lived every lap, every fall, and every podium.*

---

## The story behind the app

Sixteen years on a BMX Racing track teach you many things: how to read a berm, how to explode off the gate, how to get back up after a crash. What they don't teach you is how to measure any of it.

In BMX Racing clubs around the world, training is still managed the way it was decades ago — handwritten notebooks, whiteboard routines erased and rewritten every week, race times shouted across the track and forgotten by Tuesday. Coaches carry everything in their heads. Athletes have no way of knowing how much stronger they've gotten since January, or whether their gate time has improved over the last three months.

**Holeshot** was born out of that frustration.

Named after the most coveted position in BMX Racing — the rider who clears the first corner ahead of everyone else — Holeshot is a sports management suite built specifically for the BMX Racing ecosystem. Not a generic fitness app adapted to fit. Not a spreadsheet dressed up with a logo. A tool designed from the inside out, by someone who has raced, trained, fallen, and competed for over a decade, for the coaches and athletes who deserve better than a whiteboard.

---

## What Holeshot solves

| The old way | The Holeshot way |
|---|---|
| Routines written on a whiteboard, erased weekly | Persistent gym routines with full version history |
| Athlete weights noted on paper, never compared | Physical measurement history with visual progress charts |
| Race times shouted and forgotten | Time-Trial records with sector breakdown and track conditions |
| Monthly payments tracked in a notebook | Automated payment status per athlete per club |
| No way to know if an athlete improved | 1RM tracking, time progression graphs, and goal monitoring |
| UCI licenses expiring unnoticed | Automatic alerts before licenses and insurance policies expire |
| Coaches managing everything from memory | A dashboard with every athlete's status in one place |

---

## Who is Holeshot for?

Holeshot is designed around two roles that sit at the heart of every BMX Racing club:

### 🏋️ Coaches & Physical Trainers
You put in the hours building training plans, tracking loads, and pushing athletes toward their potential. Holeshot gives you a digital space to do all of that — assign gym routines individually or to the whole squad, record Time-Trial results by sector, monitor who's improving and who needs attention, and communicate changes to your group without relying on external messaging apps.

### 🚲 Athletes
You train hard. You deserve to see the proof. Holeshot gives every athlete a personal dashboard with their complete training history, gym load progression, personal bests, upcoming competitions, and active goals — all in one place, always in your pocket.

---

## Core features

### 👤 Athlete & Coach Profiles
Complete digital profiles for every member of the club. Athletes carry their UCI category, license number and expiration date, insurance policy details, physical measurements, injury history, and even their bicycle specs — frame brand, tire size, cranks, gear — all in one place.

### 🏟️ Club & Membership Management
Holeshot understands that BMX Racing isn't a single-team sport. Multiple clubs coexist, each with its own coaches, methodology, schedules, and fee structure. The same athlete can belong to their home club **and** the Departmental Selection simultaneously, with independent memberships, roles, and payment flows for each.

Supported roles within each club:
- **Athlete** — competes and trains under the club
- **Coach** — plans and manages technical track training
- **Physical Trainer** — designs and tracks gym-based conditioning

A coach can be a coach in one club and an athlete in another. The system handles it cleanly.

### 💳 Monthly Payment Tracking
Payment tracking is tied directly to each membership. Each athlete's fee, payment status (on time, pending, or overdue), method, and voucher are registered per club. Coaches get automatic alerts when a payment is approaching its due date.

### 🏋️ Gym Routine Planning
Coaches build structured gym routines with exercises organized by day of the week, sets, reps, suggested load, order, and notes. Routines can be assigned to the full squad or to individual athletes. Every version of a routine is preserved — when you update a plan, the historical records of what was actually executed remain intact.

Athletes log their actual weights per set per session directly from the app. No more trying to remember what you lifted last Thursday.

### 📈 Progress Tracking
This is where Holeshot earns its name.

- **1RM Records:** Track one-rep-max results per exercise over time with date-stamped entries
- **Load Progression:** Visual charts of weight lifted per exercise across sessions
- **Physical Evolution:** Weight and height history graphed over time
- **Goal Monitoring:** Set quantifiable goals with deadlines ("reach a 100 kg squat 1RM by August 30") and track progress with a live indicator

### ⏱️ Track Performance & Testing
Coaches register Time-Trial results per athlete per session, broken down by sector: gate time (first 8 meters), first straight, sector 2, and sector 3 — plus total time and track condition. Athletes see their time progression charted across sessions.

The test system is built to grow: Time-Trials are the first test type, but the architecture supports jump tests, group checks, and any new test format coaches define.

### 🏆 Competition Management
Register upcoming competitions with name, date, location, level, and type. Add athletes to each event and record results afterward — final position, phase reached (qualifying, quarterfinal, semifinal, final), and race time. Every athlete's competitive history is preserved in their profile.

### 🔔 Notifications & Alerts
The system proactively notifies users about:
- Upcoming training sessions
- New routine assignments
- Pending or overdue monthly payments
- UCI license expiration (approaching and expired)
- Insurance policy expiration
- Goal deadlines approaching

### 📣 Club Announcements
Coaches can post announcements to their full club or to a specific group (like the Departmental Selection), covering schedule changes, cancellations, call-ups, or any relevant club news — without relying on external messaging apps.

---

## Technical overview

Holeshot is built as a RESTful API following hexagonal architecture principles, ensuring that business logic remains completely independent of any framework, database, or delivery mechanism.

| Layer | Technology |
|---|---|
| Backend | Spring Boot 3 · Java 21 |
| Security | Spring Security · JWT |
| Persistence | Spring Data JPA · Hibernate |
| Database (dev) | H2 (in-memory) |
| Database (prod) | PostgreSQL |
| Migrations | Flyway |
| API Docs | SpringDoc OpenAPI (Swagger UI) |
| Frontend | Next.js · TailwindCSS *(planned)* |

### Architecture

```
io.holeshot.core
├── domain
│   ├── model          ← Pure JPA entities, no framework dependencies
│   │   ├── user
│   │   ├── club
│   │   ├── training
│   │   ├── test
│   │   └── shared     ← Auditable base class
│   └── port
│       ├── in         ← Use case interfaces
│       └── out        ← Repository interfaces
├── application
│   └── service        ← Use case implementations
├── infrastructure
│   ├── persistence    ← JPA repository implementations
│   ├── web            ← REST controllers + DTOs
│   └── security       ← JWT filter, Spring Security config
└── shared
    ├── exception       ← Global error handling
    └── config          ← Auditing, beans
```

### Design principles

- **Hexagonal architecture:** The domain knows nothing about Spring, JPA, or HTTP. Ports define what the domain needs; adapters implement it.
- **Role-based access control in context:** A user's role is not global — it belongs to their membership in a specific club. The same person can be a coach in one club and an athlete in another.
- **Immutable history:** Physical measurements, load records, Time-Trial results, and 1RM records are never overwritten. Every entry is a new timestamped row. Progress graphs are always accurate.
- **Soft delete everywhere:** No data is permanently destroyed. Entities carry a `deletedAt` timestamp and are filtered transparently at the database level.
- **Versioned routines:** When a coach updates a routine, a new version is created. Historical load records always reference the exact version of the routine that was executed.
- **Mobile-first:** Designed for the athlete standing in the gym, phone in hand, logging weights between sets.

---

## Domain model — modules

```
┌─────────────────────────────────────────────────────────────┐
│                         HOLESHOT                            │
├──────────────┬──────────────┬──────────────┬───────────────┤
│   domain     │   domain     │   domain     │   domain      │
│   .user      │   .club      │   .training  │   .test       │
│              │              │              │               │
│ User         │ Club         │ Exercise     │ Test          │
│ UserProfile  │ Role         │ Routine      │ TimeTrial     │
│ UciLicense   │ Permission   │ RoutineEx.   │ TestType      │
│ Insurance    │ Enrollment ◄─┼─────────────►│               │
│ PhysMeasure  │ ClubSession  │ Assignment   │               │
│ Injury       │ Attendance   │ LoadRecord   │               │
│ Bicycle      │ MonthlyPay.  │ OneRmRecord  │               │
└──────────────┴──────────────┴──────────────┴───────────────┘
                      ▲
              Enrollment is the
              axis of the model.
              Everything contextual
              anchors here.
```

---

## Roadmap

The first version of Holeshot targets the core MVP: profiles, memberships, gym routines, progress tracking, and Time-Trial recording. Subsequent iterations will expand the suite incrementally.

- **v1.0** — Profiles · Clubs · Memberships · Gym routines · Load tracking · Time-Trials
- **v1.1** — Competition management · Results history
- **v1.2** — Goal setting · Achievement tracking · Progress dashboards
- **v1.3** — Notifications · Payment alerts · License expiration warnings
- **v2.0** — Next.js frontend · PWA · Mobile-first UI
- **v2.x** — Nutritional plans · Health recommendations · Injury-aware load suggestions

---

## Contributing

Holeshot is a personal project with a real-world purpose — bringing a sport that runs on passion into the digital age it deserves. If you ride, coach, or simply believe that good software can make great sports even better, contributions are welcome.

---

<p align="center">
  <i>"The holeshot doesn't go to the fastest rider. It goes to the one who was most prepared."</i>
</p>